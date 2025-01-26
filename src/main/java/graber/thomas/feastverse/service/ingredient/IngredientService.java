package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IngredientService {
    Page<IngredientType> getAllTypes(String name, Pageable pageable);

    Optional<IngredientType> getIngredientTypeById(Long id);

    Page<Ingredient> getAllIngredients(String name, Long ingredientTypeId, String ingredientTypeName, Pageable pageable);

    Optional<Ingredient> getIngredientById(Long id);
}
