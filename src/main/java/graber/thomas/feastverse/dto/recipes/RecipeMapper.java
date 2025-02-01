package graber.thomas.feastverse.dto.recipes;

import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeIngredient;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    @Mapping(target = "type_name", source = "type.name")
    @Mapping(target = "owner_id", source = "owner.id")
    @Mapping(target = "id", source = "id")
    RecipeViewDto toRecipeViewDto(Recipe recipe);

    List<RecipeViewDto> toRecipeViewDtoList(List<Recipe> recipes);

    @Mapping(target = "ingredient_name", source = "ingredient.name")
    RecipeIngredientViewDto toRecipeIngredientViewDto(RecipeIngredient ingredient);

    List<RecipeIngredientViewDto> toRecipeIngredientViewDtoList(List<RecipeIngredient> ingredients);

    RecipeStepViewDto toRecipeStepViewDto(RecipeStep step);

    List<RecipeStepViewDto> toRecipeStepViewDtoList(List<RecipeStep> steps);
}
