package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientTypeRepository ingredientTypeRepository;

    public IngredientServiceImpl(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    @Override
    public Page<IngredientType> getAllTypes(Pageable pageable) {
        return ingredientTypeRepository.findAll(pageable);
    }
}
