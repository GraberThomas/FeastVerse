package graber.thomas.feastverse.service.recipes;

import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import graber.thomas.feastverse.model.recipes.RecipeType;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {
    List<RecipeType> getAllRecipesTypes(String name);

    Optional<RecipeType> getRecipeType(UUID id);

    Optional<RecipeType> createRecipeType(String name);

    Optional<RecipeType> updateRecipeType(UUID id, String name);

    void deleteRecipeType(UUID id);

    //Recipes
    Optional<Recipe> getRecipeById(UUID id);

    Page<Recipe> findAllRecipes(String name, Integer maxTotalTime, Integer servingSize, RecipeDifficulty difficulty, RecipeDifficulty maxDifficulty, UUID type, List<UUID> withIngredient, List<Long> withIngredientType, List<Long> withoutIngredientType, List<String> withTags, UUID ownerId, VisibilityFilter visibility, DeletedFilter deletedStatus, Pageable pageable);
}
