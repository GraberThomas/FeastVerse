package graber.thomas.feastverse.repository.recipes;

import graber.thomas.feastverse.model.recipes.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, UUID> {
}
