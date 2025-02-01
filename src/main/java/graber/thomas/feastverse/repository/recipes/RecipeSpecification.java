package graber.thomas.feastverse.repository.recipes;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import graber.thomas.feastverse.model.recipes.RecipeIngredient;
import graber.thomas.feastverse.model.recipes.RecipeType;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class RecipeSpecification {
    public static Specification<Recipe> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("title")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Recipe> hasMaxTotalTime(Integer maxTotalTime) {
        return (root, query, cb) -> {
            if (maxTotalTime == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(
                    cb.sum(cb.coalesce(root.get("preparation_time"), 0), cb.coalesce(root.get("cooking_time"), 0)),
                    maxTotalTime
            );
        };
    }

    public static Specification<Recipe> hasServingSize(Integer servingSize) {
        return (root, query, cb) -> {
            if (servingSize == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("servings_size"), servingSize);
        };
    }

    public static Specification<Recipe> hasDifficulty(RecipeDifficulty difficulty) {
        return (root, query, cb) -> {
            if (difficulty == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("difficulty"), difficulty);
        };
    }

    public static Specification<Recipe> hasMaxDifficulty(RecipeDifficulty maxDifficulty) {
        return (root, query, cb) -> {
            if (maxDifficulty == null) {
                return cb.conjunction();
            }

            return cb.lessThanOrEqualTo(root.get("difficulty").as(Integer.class), maxDifficulty.ordinal());
        };
    }

    public static Specification<Recipe> hasType(UUID typeId) {
        return (root, query, cb) -> {
            if (typeId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("type").get("id"), typeId);
        };
    }

    public static Specification<Recipe> hasIngredient(List<Long> ingredientIds) {
        return (root, query, cb) -> {
            if (ingredientIds == null || ingredientIds.isEmpty()) {
                return cb.conjunction();
            }

            Join<Object, Object> recipeIngredientJoin = root.join("recipeIngredients", JoinType.LEFT);
            Join<Object, Object> ingredientJoin = recipeIngredientJoin.join("ingredient", JoinType.LEFT);
            return ingredientJoin.get("id").in(ingredientIds);
        };
    }

    public static Specification<Recipe> hasIngredientType(List<Long> ingredientTypeIds) {
        return (root, query, cb) -> {
            if (ingredientTypeIds == null || ingredientTypeIds.isEmpty()) {
                return cb.conjunction();
            }
            Join<Object, Object> recipeIngredientJoin = root.join("recipeIngredients", JoinType.LEFT);
            Join<Object, Object> ingredientJoin = recipeIngredientJoin.join("ingredient", JoinType.LEFT);

            return ingredientJoin.get("type").get("id").in(ingredientTypeIds);
        };
    }

    public static Specification<Recipe> withoutIngredientType(List<Long> ingredientTypeIds) {
        return (root, query, cb) -> {
            if (ingredientTypeIds == null || ingredientTypeIds.isEmpty()) {
                return cb.conjunction();
            }

            Subquery<UUID> subQuery = query.subquery(UUID.class);
            Root<RecipeIngredient> subRoot = subQuery.from(RecipeIngredient.class);
            Join<RecipeIngredient, Ingredient> subIngredientJoin = subRoot.join("ingredient");

            subQuery.select(subRoot.get("recipe").get("id"))
                    .where(subIngredientJoin.get("type").get("id").in(ingredientTypeIds));

            return cb.not(root.get("id").in(subQuery));
        };
    }



    public static Specification<Recipe> hasTags(List<String> tags) {
        return (root, query, cb) -> {
            if (tags == null || tags.isEmpty()) {
                return cb.conjunction();
            }

            Join<Recipe, String> tagsJoin = root.join("tags");

            if (query != null) {
                query.distinct(true);
            }

            return tagsJoin.in(tags);
        };
    }


    public static Specification<Recipe> hasVisibility(VisibilityFilter visibility) {
        return (root, query, cb) -> {
            if (visibility == null || visibility.equals(VisibilityFilter.ALL)) {
                return cb.conjunction();
            }
            if (visibility.equals(VisibilityFilter.PUBLIC)) {
                return cb.isTrue(root.get("is_public"));
            } else if (visibility.equals(VisibilityFilter.PRIVATE)) {
                return cb.isFalse(root.get("is_public"));
            }
            return cb.conjunction();
        };
    }

    public static Specification<Recipe> hasDeletedStatus(DeletedFilter deletedStatus) {
        return (root, query, cb) -> {
            if (deletedStatus == null) {
                return cb.conjunction();
            }
            if (deletedStatus.equals(DeletedFilter.DELETED)) {
                return cb.isTrue(root.get("is_deleted"));
            } else if (deletedStatus.equals(DeletedFilter.NOT_DELETED)) {
                return cb.isFalse(root.get("is_deleted"));
            }
            return cb.conjunction();
        };
    }

    public static Specification<Recipe> hasOwnerId(UUID ownerId){
        return (root, query, cb) -> {
            if(ownerId == null){
                return cb.conjunction();
            }
            return cb.equal(root.get("owner").get("id"), ownerId);
        };
    }

    public static Specification<Recipe> buildSpecification(
            String name,
            Integer maxTotalTime,
            Integer servingSize,
            RecipeDifficulty difficulty,
            RecipeDifficulty maxDifficulty,
            UUID type,
            List<Long> withIngredient,
            List<Long> withIngredientType,
            List<Long> withoutIngredientType,
            List<String> withTags,
            UUID ownerId,
            VisibilityFilter visibility,
            DeletedFilter deletedStatus
    ) {
        return Specification.where(hasName(name))
                .and(hasMaxTotalTime(maxTotalTime))
                .and(hasServingSize(servingSize))
                .and(hasDifficulty(difficulty))
                .and(hasMaxDifficulty(maxDifficulty))
                .and(hasType(type))
                .and(hasIngredient(withIngredient))
                .and(hasIngredientType(withIngredientType))
                .and(withoutIngredientType(withoutIngredientType))
                .and(hasTags(withTags))
                .and(hasVisibility(visibility))
                .and(hasDeletedStatus(deletedStatus))
                .and(hasOwnerId(ownerId));
    }
}
