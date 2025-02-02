package graber.thomas.feastverse.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "IngredientView", oneOf = {IngredientPublicViewDto.class, IngredientAdminViewDto.class})
public interface IngredientViewDto {
    Long id();
    String name();
    IngredientTypeViewDto type();
    String description();
    Boolean isPublic();
    UUID ownerId();
    String imageUrl();
}
