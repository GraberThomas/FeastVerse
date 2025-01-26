package graber.thomas.feastverse.dto.ingredient;

import java.util.UUID;

public interface IngredientViewDto {
    Long id();
    String name();
    IngredientTypeViewDto type();
    String description();
    Boolean isPublic();
    UUID ownerId();
    String imageUrl();
}
