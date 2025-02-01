package graber.thomas.feastverse.dto.recipes;

import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record RecipeUserViewDto(
        UUID id,
        String title,
        String description,
        Integer preparation_time,
        Integer cooking_time,
        Integer servings_size,
        RecipeDifficulty difficulty,
        String type_name,
        List<RecipeIngredientViewDto> recipeIngredients,
        List<RecipeStepViewDto> recipe_steps,
        Set<String> tags,
        String author_note,
        String image_file_name,
        UUID owner_id,
        boolean is_public,
        String language,
        LocalDate createdDate,
        LocalDate updatedDate
) implements RecipeViewDto {}

