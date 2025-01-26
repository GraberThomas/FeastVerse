package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.dto.ingredient.IngredientTypeViewDto;

public record IngredientViewDto(
        Long id,
        String name,
        IngredientTypeViewDto type,
        String description,
        String imageUrl
) {
}
