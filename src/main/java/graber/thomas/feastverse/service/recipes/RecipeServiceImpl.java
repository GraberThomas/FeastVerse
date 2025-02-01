package graber.thomas.feastverse.service.recipes;

import graber.thomas.feastverse.exception.ForbiddenActionException;
import graber.thomas.feastverse.exception.ResourceAlreadyExist;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeType;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.repository.recipes.RecipeRepository;
import graber.thomas.feastverse.repository.recipes.RecipeTypeRepository;
import graber.thomas.feastverse.service.security.SecurityService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeTypeRepository recipeTypeRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final RecipeRepository recipeRepository;
    private final SecurityService securityService;

    public RecipeServiceImpl(RecipeTypeRepository recipeTypeRepository, RecipeRepository recipeRepository, SecurityService securityService) {
        this.recipeTypeRepository = recipeTypeRepository;
        this.recipeRepository = recipeRepository;
        this.securityService = securityService;
    }

    @Override
    public List<RecipeType> getAllRecipesTypes(String name) {
        if (name != null) {
            return recipeTypeRepository.findAllByNameContains(name);
        }
        return recipeTypeRepository.findAll();
    }

    @Override
    public Optional<RecipeType> getRecipeType(UUID id) {
        return recipeTypeRepository.findById(id);
    }

    @Override
    public Optional<RecipeType> createRecipeType(String name) {
        logger.info("Trying to create recipe type: {}", name);
        name = name.toUpperCase();
        RecipeType recipeType = new RecipeType(name);
        recipeType = this.saveRecipeType(recipeType);
        return Optional.of(recipeType);
    }

    @Override
    public Optional<RecipeType> updateRecipeType(UUID id, String name) {
        logger.info("Trying to update recipe type with id {} with name {}", id, name);
        name = name.toUpperCase();
        RecipeType recipeType = this.recipeTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by id: " + id)
        );
        recipeType.setName(name);
        recipeType = this.recipeTypeRepository.save(recipeType);
        return Optional.of(this.recipeTypeRepository.save(recipeType));
    }

    private RecipeType saveRecipeType(RecipeType recipeType) {
        try {
            recipeType = this.recipeTypeRepository.save(recipeType);
        } catch (DataIntegrityViolationException e){
            logger.error("DataIntegrityViolationException raised. Name already exists.");
            throw new ResourceAlreadyExist(recipeType.getName());
        }
        logger.info("Success to save recipe type: {}", recipeType.getName());
        return recipeType;
    }

    @Override
    public void deleteRecipeType(UUID id) {
        this.recipeTypeRepository.deleteById(id);
    }

    // RECIPES

    //TODO: Allow get recipe if is saved by user
    @Override
    public Optional<Recipe> getRecipeById(UUID id) {
        Recipe recipe = this.recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe not found by id: " + id)
        );
        UUID userId = securityService.getCurrentUserId();

        if(securityService.hasRole(UserType.ADMINISTRATOR)){
            return Optional.of(recipe);
        }

        if(recipe.isDeleted()){
            throw new EntityNotFoundException("Recipe not found by id: " + id);
        }

        if(recipe.isPublic()){
            return Optional.of(recipe);
        }

        if(userId!= null && !recipe.getOwner().getId().equals(userId)){
            throw new ForbiddenActionException("You are not allowed to access this ingredient.");
        }

        return Optional.of(recipe);

    }
}
