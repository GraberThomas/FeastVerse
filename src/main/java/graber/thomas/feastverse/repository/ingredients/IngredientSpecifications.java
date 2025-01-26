package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class IngredientSpecifications {
    public static Specification<Ingredient> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }


    public static Specification<Ingredient> hasIngredientType(Long ingredientTypeId) {
        return (root, query, criteriaBuilder) -> {
            if (ingredientTypeId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type").get("id"), ingredientTypeId);
        };
    }

    public static Specification<Ingredient> hasIngredientTypeName(String typeName) {
        return (root, query, criteriaBuilder) -> {
            if (typeName == null || typeName.isEmpty()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("type").get("name")),
                    "%" + typeName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Ingredient> isPublic() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isPublic"), true);
    }

    public static Specification<Ingredient> isPrivate() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isPublic"), false);
    }

    public static Specification<Ingredient> isCreated() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get("owner"));
    }

    public static Specification<Ingredient> isDefault() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(root.get("owner"));
    }

    public static Specification<Ingredient> hasOwner(UUID ownerId) {
        return (root, query, criteriaBuilder) -> {
            if (ownerId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
        };
    }

}
