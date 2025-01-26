package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientTypeMapper {
    default IngredientTypeViewDto fromEntityMinimal(IngredientType type) {
        return IngredientTypeViewDto.fromEntityMinimal(type);
    }
}
