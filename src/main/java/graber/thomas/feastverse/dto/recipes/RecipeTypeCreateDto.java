package graber.thomas.feastverse.dto.recipes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "RecipeTypeCreateDto", description = "Schema needed to create or update a recipe type")
public record RecipeTypeCreateDto(
        @NotEmpty
        String name
) {}
