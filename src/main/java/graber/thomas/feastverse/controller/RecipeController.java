package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.service.recipes.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Recipes", description = "Endpoints for recipes")
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    @GetMapping("/{recipeId}")
    public RecipeViewDto getRecipeById(@PathVariable UUID recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe found for id " + recipeId + ".")
        );

        return recipeMapper.toRecipeViewDto(recipe);
    }
}
