package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.springframework.data.jpa.domain.Specification;

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
}
