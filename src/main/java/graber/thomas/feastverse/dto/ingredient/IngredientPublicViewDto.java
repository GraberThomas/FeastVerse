package graber.thomas.feastverse.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "IngredientPublicView")
public record IngredientPublicViewDto(
        Long id,
        String name,
        IngredientTypeViewDto type,
        String description,
        Boolean isPublic,
        UUID ownerId,
        String imageUrl
) implements IngredientViewDto {}
