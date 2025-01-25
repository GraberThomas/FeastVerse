package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.Ingredient;

public record IngredientViewDto(
        Long id,
        String name,
        IngredientTypeViewDto type,
        String description,
        String imageUrl
) {
    public static IngredientViewDto fromEntity(Ingredient entity) {
        return new IngredientViewDto(
                entity.getId(),
                entity.getName(),
                IngredientTypeViewDto.fromEntityMinimal(entity.getType()),
                entity.getDescription(),
                entity.getImage_url()
        );
    }
}
