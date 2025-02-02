package graber.thomas.feastverse.dto.ingredient;

import com.fasterxml.jackson.annotation.JsonInclude;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

@Schema(name = "IngredientTypeView")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record IngredientTypeViewDto(
        Long id,
        String name,
        @Nullable
        String description,
        @Nullable
        String imageUrl
) {
    public static IngredientTypeViewDto fromEntityMinimal(IngredientType entity) {
        return new IngredientTypeViewDto(
                entity.getId(),
                entity.getName(),
                null,
                null
        );
    }

    public static IngredientTypeViewDto fromEntity(IngredientType entity) {
        return new IngredientTypeViewDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage_url()
        );
    }
}
