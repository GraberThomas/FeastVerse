package graber.thomas.feastverse.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Schema(name = "IngredientCreate")
public record IngredientCreateDto(
        @NotEmpty
        String name,

        @Length(max = 5000)
        String description,

        @NotNull
        Long typeId,

        @NotNull
        Boolean isPublic
) {
}
