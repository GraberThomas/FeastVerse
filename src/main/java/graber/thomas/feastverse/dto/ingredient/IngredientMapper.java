package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.utils.ImageUrlResolver;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    private final ImageUrlResolver imageUrlResolver;

    public IngredientMapper(ImageUrlResolver imageUrlResolver) {
        this.imageUrlResolver = imageUrlResolver;
    }

    public IngredientViewDto toViewDto(Ingredient entity) {
        return new IngredientViewDto(
                entity.getId(),
                entity.getName(),
                IngredientTypeViewDto.fromEntityMinimal(entity.getType()),
                entity.getDescription(),
                entity.getImage_file_name() != null ?
                    imageUrlResolver.resolveUrl(
                            entity.getOwner() == null,
                            "ingredient",
                            entity.getImage_file_name(),
                            entity.getImage_file_name()
                    )
                :
                null
        );
    }
}

