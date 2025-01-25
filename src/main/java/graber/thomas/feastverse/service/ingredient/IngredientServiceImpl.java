package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientSpecifications;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientTypeRepository ingredientTypeRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientTypeRepository ingredientTypeRepository, IngredientRepository ingredientRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Page<IngredientType> getAllTypes(String name, Pageable pageable) {
        Specification<IngredientType> spec = IngredientTypeSpecifications.hasName(name);
        return ingredientTypeRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<IngredientType> getById(Long id) {
        return ingredientTypeRepository.findById(id);
    }

    @Override
    public Page<Ingredient> getAllIngredients(String name, Long IngredientTypeId, Pageable pageable) {
        Specification<Ingredient> spec = IngredientSpecifications.hasName(name);
        spec = spec.and(IngredientSpecifications.hasIngredientType(IngredientTypeId));
        return this.ingredientRepository.findAll(spec, pageable);
    }
}
