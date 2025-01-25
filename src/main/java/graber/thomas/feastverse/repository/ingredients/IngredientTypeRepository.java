package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IngredientTypeRepository extends JpaRepository<IngredientType, Long>, JpaSpecificationExecutor<IngredientType> {
    IngredientType getIngredientTypeById(Long id);
}
