package graber.thomas.feastverse.dto.ingredient;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.utils.ImageUrlResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = { IngredientTypeMapper.class })
public abstract class IngredientMapper {

    @Autowired
    protected ImageUrlResolver imageUrlResolver;

    @Mapping(target = "isPublic", source = "public")
    @Mapping(target = "isDeleted", source = "deleted")
    @Mapping(target = "ownerId", source = "owner", qualifiedByName = "mapOwnerId")
    @Mapping(target = "type", source = "type", qualifiedByName = "mapIngredientType")
    @Mapping(target = "imageUrl", source = ".", qualifiedByName = "resolveImageUrl")
    public abstract IngredientAdminViewDto toAdminViewDto(Ingredient ingredient);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapIngredientType")
    @Mapping(target = "imageUrl", source = ".", qualifiedByName = "resolveImageUrl")
    public abstract IngredientPublicViewDto toPublicViewDto(Ingredient ingredient);

    @Named("mapOwnerId")
    protected UUID mapOwnerId(User owner) {
        return owner != null ? owner.getId() : null;
    }

    @Named("mapIngredientType")
    protected IngredientTypeViewDto mapIngredientType(IngredientType type) {
        return IngredientTypeViewDto.fromEntityMinimal(type);
    }

    @Named("resolveImageUrl")
    protected String resolveImageUrl(Ingredient ingredient) {
        return ingredient.getImage_file_name() != null
                ? imageUrlResolver.resolveUrl(
                ingredient.getOwner() == null,
                "ingredient",
                ingredient.getImage_file_name(),
                ingredient.getImage_file_name()
        )
                : null;
    }
}
