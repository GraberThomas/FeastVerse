package graber.thomas.feastverse.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "IngredientAdminView")
public record IngredientAdminViewDto(
        Long id,
        String name,
        IngredientTypeViewDto type,
        String description,
        Boolean isPublic,
        Boolean isDeleted,
        UUID ownerId,
        String imageUrl,
        LocalDate createdDate,
        LocalDate updatedDate
) implements IngredientViewDto {}
