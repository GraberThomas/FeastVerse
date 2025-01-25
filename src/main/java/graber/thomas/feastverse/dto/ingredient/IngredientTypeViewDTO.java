package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.IngredientType;

public record IngredientTypeViewDTO(
        Long id,
        String name,
        String description,
        String imageUrl
) {
    public static IngredientTypeViewDTO fromEntity(IngredientType entity) {
        return new IngredientTypeViewDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage_url()
        );
    }
}
