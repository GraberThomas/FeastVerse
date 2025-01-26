package graber.thomas.feastverse.dto.ingredient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record IngredientCreateDto(
        @NotEmpty
        String name,

        @Length(max = 5000)
        String description,

        @NotNull
        Long typeId
) {
}
