package graber.thomas.feastverse.dto.recipes;

import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeIngredient;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    // 🔹 Version liste (optimisée pour GET /recipes)
    @Mapping(target = "type_name", source = "type.name")
    RecipeListViewDto toRecipeListViewDto(Recipe recipe);

    List<RecipeListViewDto> toRecipeListViewDtoList(List<Recipe> recipes);

    // 🔹 Version utilisateur détaillée (GET /recipes/{id})
    @Mapping(target = "type_name", source = "type.name")
    @Mapping(target = "owner_id", source = "owner.id")
    RecipeUserViewDto toRecipeUserViewDto(Recipe recipe);

    // 🔹 Version administrateur détaillée (GET /recipes/{id})
    @Mapping(target = "type_name", source = "type.name")
    @Mapping(target = "owner_id", source = "owner.id")
    @Mapping(target = "is_deleted", source = "deleted")
    RecipeAdminViewDto toRecipeAdminViewDto(Recipe recipe);

    List<RecipeUserViewDto> toRecipeViewDtoList(List<Recipe> recipes);
    List<RecipeAdminViewDto> toRecipeAdminViewDtoList(List<Recipe> recipes);

    // 🔹 Mapping des ingrédients
    @Mapping(target = "ingredient_name", source = "ingredient.name")
    RecipeIngredientViewDto toRecipeIngredientViewDto(RecipeIngredient ingredient);

    List<RecipeIngredientViewDto> toRecipeIngredientViewDtoList(List<RecipeIngredient> ingredients);

    // 🔹 Mapping des étapes de recette
    RecipeStepViewDto toRecipeStepViewDto(RecipeStep step);

    List<RecipeStepViewDto> toRecipeStepViewDtoList(List<RecipeStep> steps);
}
