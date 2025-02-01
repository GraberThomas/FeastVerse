package graber.thomas.feastverse.repository.recipes;

import graber.thomas.feastverse.model.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}
