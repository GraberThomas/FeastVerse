package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IngredientRepository extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {
    Page<Ingredient> findAllByPublicTrue(Specification<Ingredient> spec, Pageable pageable);
}
