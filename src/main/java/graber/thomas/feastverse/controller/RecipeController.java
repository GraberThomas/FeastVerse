package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.comment.CommentCreateDto;
import graber.thomas.feastverse.dto.comment.CommentMapper;
import graber.thomas.feastverse.dto.comment.CommentViewDto;
import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.dto.reports.ReportCreateDto;
import graber.thomas.feastverse.dto.reports.ReportMapper;
import graber.thomas.feastverse.dto.reports.ReportOnTargetCreateDto;
import graber.thomas.feastverse.dto.reports.ReportViewDTO;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.comment.CommentService;
import graber.thomas.feastverse.service.like.LikeService;
import graber.thomas.feastverse.service.like.LikeServiceImpl;
import graber.thomas.feastverse.service.recipes.RecipeService;
import graber.thomas.feastverse.service.report.ReportService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    private final ReportService reportService;
    private final ReportMapper reportMapper;
    private final UserService userService;
    private final LikeService likeService;


    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper, SecurityService securityService, CommentService commentService, CommentMapper commentMapper, ReportService reportService, ReportMapper reportMapper, UserService userService, LikeService likeService) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
        this.securityService = securityService;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.reportService = reportService;
        this.reportMapper = reportMapper;
        this.userService = userService;
        this.likeService = likeService;
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
            @ParameterObject
            @PageableDefault(size = 10, sort = "likeCount", direction = Sort.Direction.DESC) Pageable pageable
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
    public Page<CommentViewDto> getAllCommentsOnRecipe(
            @PathVariable UUID recipeId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
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
    public Page<CommentViewDto> getAllCommentsOnRecipeStep(
            @PathVariable UUID recipeStepId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        RecipeStep recipeStep = recipeService.getRecipeStepById(recipeStepId).orElseThrow(
                () -> new EntityNotFoundException("No recipe step found for id " + recipeStepId + ".")
        );

        return getCommentViewDtos(pageable, recipeStep.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{recipeId}/reports")
    public ResponseEntity<Void> reportRecipe(
            @PathVariable UUID recipeId,
            @Valid @RequestBody ReportOnTargetCreateDto reportOnTargetCreateDto
    ){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        if(reportOnTargetCreateDto.targetId() != null){
            if(!reportOnTargetCreateDto.targetId().equals(recipe.getId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossible to create the report. Target id mismatch with recipe id.");
            }
        }

        ReportCreateDto reportCreateDto = new ReportCreateDto(
                recipeId,
                reportOnTargetCreateDto.message(),
                reportOnTargetCreateDto.type()
        );

        Report newReport = reportService.create(reportCreateDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create report.")
        );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/reports/{id}")
                .buildAndExpand(newReport.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    @GetMapping("/{recipeId}/reports")
    public Page<ReportViewDTO> getAllReportsOnRecipe(
            @PathVariable UUID recipeId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        Page<Report> reports = reportService.getAll(
                null,
                null,
                recipe.getId(),
                null,
                pageable
        );

        return reports.map(reportMapper::toReportView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/steps/{stepId}/reports")
    public ResponseEntity<Void> reportRecipeStep(
            @PathVariable UUID stepId,
            @Valid @RequestBody ReportOnTargetCreateDto reportOnTargetCreateDto
    ){
        RecipeStep recipeStep = recipeService.getRecipeStepById(stepId).orElseThrow(
                () -> new EntityNotFoundException("No recipe step found for id " + stepId + ".")
        );

        if(reportOnTargetCreateDto.targetId() != null){
            if(!reportOnTargetCreateDto.targetId().equals(recipeStep.getId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossible to create the report. Target id mismatch with recipe id.");
            }
        }

        ReportCreateDto reportCreateDto = new ReportCreateDto(
                stepId,
                reportOnTargetCreateDto.message(),
                reportOnTargetCreateDto.type()
        );

        Report newReport = reportService.create(reportCreateDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create report.")
        );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/reports/{id}")
                .buildAndExpand(newReport.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    @GetMapping("/steps/{recipeStepId}/reports")
    public Page<ReportViewDTO> getAllReportsOnRecipeStep(
            @PathVariable UUID recipeStepId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable
    ){
        RecipeStep recipeStep = recipeService.getRecipeStepById(recipeStepId).orElseThrow(
                () -> new EntityNotFoundException("No recipe step found for id " + recipeStepId + ".")
        );

        Page<Report> reports = reportService.getAll(
                null,
                null,
                recipeStep.getId(),
                null,
                pageable
        );

        return reports.map(reportMapper::toReportView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{recipeId}/like")
    public ResponseEntity<Void> likeRecipe(@PathVariable UUID recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        UUID userId = securityService.getCurrentUserId();
        User user = userService.getById(userId).orElseThrow(
                () -> new EntityNotFoundException("No user found for id " + userId + ".")
        );

        likeService.addLikeToRecipe(recipe, user).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to like recipe.")
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{recipeId}/like")
    public ResponseEntity<Void> unlikeRecipe(@PathVariable UUID recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        UUID userId = securityService.getCurrentUserId();
        User user = userService.getById(userId).orElseThrow(
                () -> new EntityNotFoundException("No user found for id " + userId + ".")
        );

        likeService.deleteLikeFromRecipe(recipe, user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // UTILS METHODS

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
