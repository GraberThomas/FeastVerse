package graber.thomas.feastverse.repository.recipes;

import graber.thomas.feastverse.model.recipes.RecipeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeTypeRepository extends JpaRepository<RecipeType, UUID> {
    List<RecipeType> findAllByNameContains(String name);
    RecipeType findByName(String name);
}