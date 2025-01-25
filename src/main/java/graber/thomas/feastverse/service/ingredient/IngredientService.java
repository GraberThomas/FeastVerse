package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientService {
    Page<IngredientType> getAllTypes(Pageable pageable);
}
