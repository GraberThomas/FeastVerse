package graber.thomas.feastverse.dto.ingredient;

import java.util.UUID;

public record IngredientPublicViewDto(
        Long id,
        String name,
        IngredientTypeViewDto type,
        String description,
        Boolean isPublic,
        UUID ownerId,
        String imageUrl
) implements IngredientViewDto {}
