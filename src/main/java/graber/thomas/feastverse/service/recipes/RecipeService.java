package graber.thomas.feastverse.service.recipes;

import graber.thomas.feastverse.model.recipes.RecipeType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {
    List<RecipeType> getAllRecipesTypes(String name);

    Optional<RecipeType> getRecipeType(UUID id);

    Optional<RecipeType> createRecipeType(String name);

    Optional<RecipeType> updateRecipeType(UUID id, String name);

    void deleteRecipeType(UUID id);
}
