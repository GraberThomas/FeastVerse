package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.comment.CommentCreateDto;
import graber.thomas.feastverse.dto.comment.CommentMapper;
import graber.thomas.feastverse.dto.comment.CommentViewDto;
import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.comment.CommentService;
import graber.thomas.feastverse.service.recipes.RecipeService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Recipes", description = "Endpoints for recipes")
@Validated
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;
    private final SecurityService securityService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private final CommentService commentService;
    private final CommentMapper commentMapper;


    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper, SecurityService securityService, CommentService commentService, CommentMapper commentMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
        this.securityService = securityService;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/{recipeId}")
    public RecipeViewDto getRecipeById(@PathVariable UUID recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        if(securityService.hasRole(UserType.ADMINISTRATOR)) {
            return recipeMapper.toRecipeAdminViewDto(recipe);
        }
        return recipeMapper.toRecipeUserViewDto(recipe);
    }

    @GetMapping
    public Page<RecipeViewDto> getAllRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer maxTotalTime,
            @RequestParam(required = false) Integer servingSize,
            @RequestParam(required = false) RecipeDifficulty difficulty,
            @RequestParam(required = false) RecipeDifficulty maxDifficulty,
            @RequestParam(required = false) UUID typeId,
            @RequestParam(required = false) @Size(max = 5, message = "Maximum 5 ingredient types allowed") List<Long> withIngredient,
            @RequestParam(required = false) @Size(max = 5, message = "Maximum 5 ingredient types allowed") List<Long> withIngredientType,
            @RequestParam(required = false) @Size(max = 5, message = "Maximum 5 excluded ingredient types allowed") List<Long> withoutIngredientType,
            @RequestParam(required = false) @Size(max = 5, message = "Maximum 5 tags allowed") List<String> withTags,
            @RequestParam(required = false) UUID ownerId,
            @RequestParam(required = false, defaultValue = "PUBLIC") VisibilityFilter visibility,
            @RequestParam(required = false, defaultValue = "NOT_DELETED") DeletedFilter deletedStatus,
            Pageable pageable
    ){
        Page<Recipe> recipes = this.recipeService.findAllRecipes(
                name,
                maxTotalTime,
                servingSize,
                difficulty,
                maxDifficulty,
                typeId,
                withIngredient,
                withIngredientType,
                withoutIngredientType,
                withTags,
                ownerId,
                visibility,
                deletedStatus,
                pageable
        );

        return recipes.map(recipeMapper::toRecipeListViewDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{recipeId}/comments")
    public ResponseEntity<Void> addCommentOnRecipe(
            @Valid @RequestBody CommentCreateDto commentCreateDto,
            @PathVariable UUID recipeId
    ){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        UUID userId = securityService.getCurrentUserId();

        URI location = createAbstractComment(recipe, commentCreateDto, userId);

        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{recipeId}/comments")
    public Page<CommentViewDto> getAllCommentsOnRecipe(@PathVariable UUID recipeId, Pageable pageable){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        return getCommentViewDtos(pageable, recipe.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/steps/{recipeStepId}/comments")
    public ResponseEntity<Void> addCommentOnRecipeStep(
            @Valid @RequestBody CommentCreateDto commentCreateDto,
            @PathVariable UUID recipeStepId
    ){
        RecipeStep recipeStep = recipeService.getRecipeStepById(recipeStepId).orElseThrow(
                () -> new EntityNotFoundException("No recipe step found for id " + recipeStepId + ".")
        );

        UUID userId = securityService.getCurrentUserId();

        URI location = createAbstractComment(recipeStep, commentCreateDto, userId);

        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/steps/{recipeStepId}/comments")
    public Page<CommentViewDto> getAllCommentsOnRecipeStep(@PathVariable UUID recipeStepId, Pageable pageable){
        RecipeStep recipeStep = recipeService.getRecipeStepById(recipeStepId).orElseThrow(
                () -> new EntityNotFoundException("No recipe step found for id " + recipeStepId + ".")
        );

        return getCommentViewDtos(pageable, recipeStep.getId());
    }

    private Page<CommentViewDto> getCommentViewDtos(Pageable pageable, UUID id) {
        Page<Comment> comments = commentService.getAllComment(
                null,
                id,
                DeletedFilter.NOT_DELETED,
                pageable
        );

        if(securityService.hasRole(UserType.ADMINISTRATOR) || securityService.hasRole(UserType.MODERATOR)){
            comments.map(commentMapper::toCommentAdminViewDto);
        }

        return comments.map(commentMapper::toCommentPublicViewDto);
    }

    private URI createAbstractComment(Commentable commentable, CommentCreateDto commentCreateDto, UUID userId) {
        Comment newComment = commentService.createComment(commentable, userId, commentCreateDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create comment.")
        );

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/comments/{id}")
                .buildAndExpand(newComment.getId())
                .toUri();
    }
}
