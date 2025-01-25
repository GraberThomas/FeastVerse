package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.springframework.data.jpa.domain.Specification;

public class IngredientTypeSpecifications {
    public static Specification<IngredientType> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
