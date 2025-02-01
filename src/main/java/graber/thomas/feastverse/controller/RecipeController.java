package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.recipes.RecipeService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper, SecurityService securityService) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
        this.securityService = securityService;
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
}
