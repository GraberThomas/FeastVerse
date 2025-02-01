package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.recipes.RecipeService;
import graber.thomas.feastverse.service.security.SecurityService;
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
    private final SecurityService securityService;

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
}
