package graber.thomas.feastverse.dto.recipes;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RecipeView", oneOf = {RecipeAdminViewDto.class, RecipeUserViewDto.class})
public interface RecipeViewDto {
}
