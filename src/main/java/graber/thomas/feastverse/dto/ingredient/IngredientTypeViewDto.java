package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.IngredientType;

public record IngredientTypeViewDto(
        Long id,
        String name,
        String description,
        String imageUrl
) {
    public static IngredientTypeViewDto fromEntity(IngredientType entity) {
        return new IngredientTypeViewDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage_url()
        );
    }
}
