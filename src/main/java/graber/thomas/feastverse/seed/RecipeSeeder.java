package graber.thomas.feastverse.seed;

import graber.thomas.feastverse.model.recipes.RecipeType;
import graber.thomas.feastverse.repository.recipes.RecipeTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class RecipeSeeder implements CommandLineRunner {
    private final RecipeTypeRepository recipeTypeRepository;

    public RecipeSeeder(RecipeTypeRepository recipeTypeRepository) {
        this.recipeTypeRepository = recipeTypeRepository;
    }

    public void seedRecipesTypes() {
        List<RecipeType> recipeTypes = List.of(
                new RecipeType("STARTER"),
                new RecipeType("MAIN_COURSE"),
                new RecipeType("DESSERT"),
                new RecipeType("SAUCE"),
                new RecipeType("BREAD"),
                new RecipeType("BRIOCHE"),
                new RecipeType("PASTRY"),
                new RecipeType("SOUP"),
                new RecipeType("DRINK"),
                new RecipeType("SALAD"),
                new RecipeType("SNACK"),
                new RecipeType("BREAKFAST")
        );

        recipeTypeRepository.saveAll(recipeTypes);
    }

    @Override
    public void run(String... args) throws Exception {
        seedRecipesTypes();
    }
}
