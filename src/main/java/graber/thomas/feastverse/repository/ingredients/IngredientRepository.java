package graber.thomas.feastverse.repository.ingredients;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
