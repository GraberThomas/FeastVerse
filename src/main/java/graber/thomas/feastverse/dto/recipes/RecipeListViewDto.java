package graber.thomas.feastverse.dto.recipes;

import java.util.UUID;

public record RecipeListViewDto(
        UUID id,
        String title,
        String type_name,
        Integer preparation_time,
        Integer cooking_time,
        String image_file_name
) implements RecipeViewDto {
}
