package graber.thomas.feastverse.seed;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.recipes.*;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import graber.thomas.feastverse.repository.recipes.RecipeRepository;
import graber.thomas.feastverse.repository.recipes.RecipeTypeRepository;
import graber.thomas.feastverse.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Order(3)
public class RecipeSeeder implements CommandLineRunner {
    private final RecipeTypeRepository recipeTypeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientTypeRepository ingredientTypeRepository;

    public RecipeSeeder(RecipeTypeRepository recipeTypeRepository, UserRepository userRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientTypeRepository ingredientTypeRepository) {
        this.recipeTypeRepository = recipeTypeRepository;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientTypeRepository = ingredientTypeRepository;
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

    public void seedRecipes() {
        RecipeType salad = recipeTypeRepository.findByName("SALAD");
        RecipeType main_course = recipeTypeRepository.findByName("MAIN_COURSE");
        RecipeType starter = recipeTypeRepository.findByName("STARTER");
        RecipeType dessert = recipeTypeRepository.findByName("DESSERT");
        RecipeType soup = recipeTypeRepository.findByName("SOUP");

        User creator = userRepository.findUserByEmail("emacron@gmail.com");
        if (creator == null) {
            throw new RuntimeException("Error in creating recipe. User not found");
        }

        Ingredient poivre = ingredientRepository.findByName("Poivre");
        Ingredient sel = ingredientRepository.findByName("Sel");
        Ingredient farine = ingredientRepository.findByName("Farine");
        Ingredient huile_olive = ingredientRepository.findByName("Huile d'olive");
        Ingredient laitue = ingredientRepository.findByName("Laitue");
        Ingredient parmesan = ingredientRepository.findByName("Parmesan");
        Ingredient pain = ingredientRepository.findByName("Pain de campagne");
        Ingredient tabasco = ingredientRepository.findByName("Tabasco");
        Ingredient moutarde = ingredientRepository.findByName("Moutarde");
        Ingredient oeuf = ingredientRepository.findByName("Oeuf");
        Ingredient capre = ingredientRepository.findByName("Câpre");
        Ingredient citron = ingredientRepository.findByName("Citron jaune");
        Ingredient ail = ingredientRepository.findByName("Ail");

        Ingredient tomate = ingredientRepository.findByName("Tomate");
        Ingredient mozzarella = ingredientRepository.findByName("Mozzarella");
        Ingredient basilic = ingredientRepository.findByName("Basilic");
        Ingredient carotte = ingredientRepository.findByName("Carotte");
        Ingredient pomme_de_terre = ingredientRepository.findByName("Pomme de terre");
        Ingredient poireau = ingredientRepository.findByName("Poireau");
        Ingredient creme = ingredientRepository.findByName("Crème fraîche");
        Ingredient beurre = ingredientRepository.findByName("Beurre");
        Ingredient pomme = ingredientRepository.findByName("Pomme");
        Ingredient sucre = ingredientRepository.findByName("Sucre");
        Ingredient cannelle = ingredientRepository.findByName("Cannelle");
        Ingredient courgette = ingredientRepository.findByName("Courgette");
        Ingredient emmental = ingredientRepository.findByName("Emmental");
        Ingredient pain_hamburger = ingredientRepository.findByName("Pain pour hamburger");
        Ingredient steak = ingredientRepository.findByName("Steak (cheval)");
        Ingredient oignon = ingredientRepository.findByName("Oignon");
        Ingredient riz_arborio = ingredientRepository.findByName("Riz italien arborio");
        Ingredient champignon = ingredientRepository.findByName("Champignons");
        Ingredient bouillon = ingredientRepository.findByName("Bouillon de volaille");
        Ingredient vin_blanc = ingredientRepository.findByName("Vin blanc");
        Ingredient vanille = ingredientRepository.findByName("Vanille");
        Ingredient lait = ingredientRepository.findByName("Lait");
        Ingredient herbe_de_provence = ingredientRepository.findByName("Herbe de provence");
        Ingredient eau = ingredientRepository.findByName("Eau");
        Ingredient chevre = ingredientRepository.findByName("Fromage de chèvre");
        Ingredient filet_mignon_porc = ingredientRepository.findByName("Filet mignon de porc");
        Ingredient haricots = ingredientRepository.findByName("Haricot vert");

        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Salade César");
        recipe1.setDescription("Une salade de saison, simple et légère");
        recipe1.setPreparation_time(20);
        recipe1.setDifficulty(RecipeDifficulty.VERY_EASY);
        recipe1.setCooking_time(null);
        recipe1.setServings_size(4);
        recipe1.setImage_file_name(null);
        recipe1.setLanguage("fr");
        recipe1.setDeleted(false);
        recipe1.setPublic(true);
        recipe1.setCreatedDate(LocalDate.now());
        recipe1.setOwner(creator);

        List<RecipeIngredient> recipeIngredients1 = List.of(
                new RecipeIngredient(recipe1, huile_olive, 2.0, QuantityType.TBSP, null),
                new RecipeIngredient(recipe1, laitue, 2.0, QuantityType.HEART, QuantityState.CHOPPED),
                new RecipeIngredient(recipe1, parmesan, 25.0, QuantityType.G, QuantityState.FLAKED),
                new RecipeIngredient(recipe1, pain, 4.0, QuantityType.SLICE, QuantityState.CRUSY),
                new RecipeIngredient(recipe1, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe1, poivre, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe1, tabasco, 1.0, QuantityType.DASH, null),
                new RecipeIngredient(recipe1, moutarde, 0.5, QuantityType.TSP, null),
                new RecipeIngredient(recipe1, oeuf, 1.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe1, parmesan, 25.0, QuantityType.G, QuantityState.GRATED),
                new RecipeIngredient(recipe1, capre, 2.0, QuantityType.TSP, null),
                new RecipeIngredient(recipe1, citron, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe1, ail, 1.0, QuantityType.CLOVE, QuantityState.PEELED)
        );

        recipe1.setRecipeIngredients(recipeIngredients1);

        List<RecipeStep> recipeSteps = List.of(
                new RecipeStep(recipe1, 1, "Faites dorer le pain, coupé en cubes, 3 min dans un peu d'huile."),
                new RecipeStep(recipe1, 2, "Déchirez les feuilles de romaine dans un saladier, et ajoutez les croûtons préalablement épongés dans du papier absorbant."),
                new RecipeStep(recipe1, 3, "Préparez la sauce : Faites cuire l'oeuf 1 min 30 dans l'eau bouillante, et rafraîchissez-le."),
                new RecipeStep(recipe1, 4, "Cassez-le dans le bol d'un mixeur et mixez, avec tous les autres ingrédients; rectifiez l'assaissonnement et incorporez à la salade. "),
                new RecipeStep(recipe1, 5, "Décorez de copeaux de parmesan, et servez.")
        );

        recipe1.setRecipe_steps(recipeSteps);
        recipe1.setType(salad);
        recipe1.setTags(Set.of("vegetarien", "salade Cesar", "entrée", "salade"));

        recipe1.setAuthor_note("Servir en accompagnement d'une quiche ou d'une tourte. Excellent repas du soir. Vous pouvez utiliser des croûtons déjà prêts. La sauce peut être préparée 6 heures à l'avance et réservée au frais.");
        recipeRepository.save(recipe1);

        // -----------------------------------------------------------------------
        // Recette 2 : Tortilla facile (Espagne)
        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Tortilla facile (Espagne)");
        recipe2.setDescription(null);
        recipe2.setPreparation_time(15);
        recipe2.setDifficulty(RecipeDifficulty.VERY_EASY);
        recipe2.setCooking_time(20);
        recipe2.setServings_size(2);
        recipe2.setLanguage("fr");
        recipe2.setDeleted(false);
        recipe2.setPublic(true);
        recipe2.setCreatedDate(LocalDate.now().minusDays(2));
        recipe2.setOwner(creator);


        List<RecipeIngredient> recipeIngredients2 = List.of(
                new RecipeIngredient(recipe2, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe2, poivre, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe2, huile_olive, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe2, oignon, 0.5, QuantityType.UNIT, null),
                new RecipeIngredient(recipe2, oeuf, 5.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe2, pomme_de_terre, 3.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe2, herbe_de_provence, null, QuantityType.NONE, null, false)
        );
        recipe2.setRecipeIngredients(recipeIngredients2);

        List<RecipeStep> recipeSteps2 = List.of(
                new RecipeStep(recipe2, 1, "Battez les oeufs en omelette dans un saladier en ajoutant le sel, le poivre et les herbes de provence."),
                new RecipeStep(recipe2, 2, "Coupez l'oignon en fines lamelles, les pommes de terre en carrés et réservez."),
                new RecipeStep(recipe2, 3, "Faites chauffer de l'huile dans une poêle, lorsque l'huile est chaude y faire cuire les pommes de terre à feu doux et les dorer en fin de cuisson (vérifier avec la pointe d'une fourchette) à feu moyen. "),
                new RecipeStep(recipe2, 4, "Ajoutez et faites rissoler les oignons."),
                new RecipeStep(recipe2, 5, "Verser les oeufs battus en omelette sur la poêle."),
                new RecipeStep(recipe2, 6, "Retournez l'omelette comme vous pouvez sur l'autre face (soit à l'aide d'une assiette, soit en la découpant en quatre morceaux et en les retournant avec une spatule) et cuisez-la."),
                new RecipeStep(recipe2, 7, "Cette omelette se déguste aussi bien chaude que froide.")
        );
        recipe2.setRecipe_steps(recipeSteps2);
        recipe2.setType(main_course);
        recipe2.setTags(Set.of("vegetarien", "tortilla", "espagnol", "entrée", "omelette", "gluten free"));
        recipe2.setAuthor_note("Attention! Les pommes de terre ne doivent en aucun cas baigner dans l'huile. On doit retourner fréquemment et surveiller la cuisson. ");
        recipeRepository.save(recipe2);

        // -----------------------------------------------------------------------
        // Recette 3 : Oeuf poché ultra simple
        Recipe recipe3 = new Recipe();
        recipe3.setTitle("Oeuf poché ultra simple");
        recipe3.setDescription("Une recette vraiment simple d'oeuf poché.");
        recipe3.setPreparation_time(0);
        recipe3.setDifficulty(RecipeDifficulty.EASY);
        recipe3.setCooking_time(5);
        recipe3.setServings_size(1);
        recipe3.setLanguage("fr");
        recipe3.setDeleted(false);
        recipe3.setPublic(true);
        recipe3.setCreatedDate(LocalDate.now().minusDays(10));
        recipe3.setOwner(creator);

        List<RecipeIngredient> recipeIngredients3 = List.of(
                new RecipeIngredient(recipe3, oeuf, 1.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe3, eau, null, QuantityType.NONE, null)
        );
        recipe3.setRecipeIngredients(recipeIngredients3);

        List<RecipeStep> recipeSteps3 = List.of(
                new RecipeStep(recipe3, 1, "Faites bouillir de l'eau dans une casserole (il faut une quantité d'eau assez importante de manière à ce que l’œuf soit recouvert lorsqu'il y sera plongé)."),
                new RecipeStep(recipe3, 2, "Quand l'eau bout fort, éteignez le feu, cassez l'œuf et mettez le délicatement dans l'eau sans la coquille (bien sûr) et n'y touchez pas !"),
                new RecipeStep(recipe3, 3, "Comptez 3 min pour le jaune très coulant, 4 min pour le jaune un peu plus pris et un peu plus pour le jaune dur."),
                new RecipeStep(recipe3, 4, "laisser le feu au minimum sous la casserole quand il y a plusieurs oeufs, compter 5 minutes pour une cuisson parfaite. "),
                new RecipeStep(recipe3, 5, "Sortez-le ensuite à l'aide d'une écumoire."),
                new RecipeStep(recipe3, 6, "Et voilà, il n'y plus rien à faire, il est prêt !")
        );
        recipe2.setRecipe_steps(recipeSteps3);
        recipe2.setType(starter);
        recipe2.setTags(Set.of("vegetarien", "quick", "oeuf", "oeuf poché", "entree", "gluten free"));
        recipe2.setAuthor_note("C'est la recette la plus facile et la plus efficace pour un bel œuf poché. Il vaut mieux cuire des oeufs qui sont à température ambiante, pour éviter qu'ils éclatent. Pour cela il faut les sortir du réfrigérateur 1 heure avant.");
        recipeRepository.save(recipe3);

        // -----------------------------------------------------------------------
        // Recette 4 : Pizza Margherita
        Recipe recipe4 = new Recipe();
        recipe4.setTitle("Pizza Margherita");
        recipe4.setDescription("Une pizza classique italienne à pâte fine et garnie d'ingrédients frais.");
        recipe4.setPreparation_time(30);
        recipe4.setDifficulty(RecipeDifficulty.MEDIUM);
        recipe4.setCooking_time(15);
        recipe4.setServings_size(2);
        recipe4.setImage_file_name(null);
        recipe4.setLanguage("fr");
        recipe4.setDeleted(false);
        recipe4.setPublic(true);
        recipe4.setCreatedDate(LocalDate.now().minusDays(5));
        recipe4.setOwner(creator);

        List<RecipeIngredient> recipeIngredients4 = List.of(
                new RecipeIngredient(recipe4, farine, 250.0, QuantityType.G, null),
                new RecipeIngredient(recipe4, sel, 1.0, QuantityType.TSP, null),
                new RecipeIngredient(recipe4, huile_olive, 2.0, QuantityType.TBSP, null),
                new RecipeIngredient(recipe4, tomate, 150.0, QuantityType.G, null),
                new RecipeIngredient(recipe4, mozzarella, 125.0, QuantityType.G, null),
                new RecipeIngredient(recipe4, basilic, 5.0, QuantityType.LEAF, null)
        );
        recipe4.setRecipeIngredients(recipeIngredients4);

        List<RecipeStep> recipeSteps4 = List.of(
                new RecipeStep(recipe4, 1, "Préparez la pâte en mélangeant farine, sel, huile et de l'eau."),
                new RecipeStep(recipe4, 2, "Laissez reposer la pâte pendant 1 heure."),
                new RecipeStep(recipe4, 3, "Étalez la pâte, ajoutez la sauce tomate, la mozzarella et le basilic."),
                new RecipeStep(recipe4, 4, "Enfournez à 220°C pendant 15 minutes.")
        );
        recipe4.setRecipe_steps(recipeSteps4);
        recipe4.setType(main_course);
        recipe4.setTags(Set.of("pizza", "italienne", "fromage", "tomate"));
        recipe4.setAuthor_note("Pour une pâte plus croustillante, utilisez de la farine type 00.");
        recipeRepository.save(recipe4);

        // -----------------------------------------------------------------------
        // Recette 5 : Soupe de légumes
        Recipe recipe5 = new Recipe();
        recipe5.setTitle("Soupe de légumes");
        recipe5.setDescription("Une soupe réconfortante aux légumes de saison.");
        recipe5.setPreparation_time(15);
        recipe5.setDifficulty(RecipeDifficulty.EASY);
        recipe5.setCooking_time(40);
        recipe5.setServings_size(4);
        recipe5.setImage_file_name(null);
        recipe5.setLanguage("fr");
        recipe5.setDeleted(false);
        recipe5.setPublic(true);
        recipe5.setCreatedDate(LocalDate.now().minusDays(3));
        recipe5.setOwner(creator);

        List<RecipeIngredient> recipeIngredients5 = List.of(
                new RecipeIngredient(recipe5, carotte, 2.0, QuantityType.UNIT, QuantityState.CHOPPED),
                new RecipeIngredient(recipe5, pomme_de_terre, 3.0, QuantityType.UNIT, QuantityState.DICED),
                new RecipeIngredient(recipe5, poireau, 1.0, QuantityType.UNIT, QuantityState.SLICED),
                new RecipeIngredient(recipe5, tomate, 200.0, QuantityType.G, null),
                new RecipeIngredient(recipe5, herbe_de_provence, 1.0, QuantityType.TSP, null),
                new RecipeIngredient(recipe5, eau, 1.0, QuantityType.L, null),
                new RecipeIngredient(recipe5, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe5, poivre, null, QuantityType.NONE, null)
        );
        recipe5.setRecipeIngredients(recipeIngredients5);

        List<RecipeStep> recipeSteps5 = List.of(
                new RecipeStep(recipe5, 1, "Épluchez et coupez les légumes en morceaux réguliers."),
                new RecipeStep(recipe5, 2, "Faites revenir légèrement les légumes dans un peu de beurre ou d'huile."),
                new RecipeStep(recipe5, 3, "Ajoutez l'eau et les herbes, puis laissez mijoter pendant 40 minutes."),
                new RecipeStep(recipe5, 4, "Mixez la soupe et ajustez l'assaisonnement.")
        );
        recipe5.setRecipe_steps(recipeSteps5);
        recipe5.setType(soup);
        recipe5.setTags(Set.of("soupe", "légumes", "vegetarien", "confort"));
        recipe5.setAuthor_note("Une touche de crème fraîche en fin de cuisson apporte de la douceur.");
        recipeRepository.save(recipe5);

        // -----------------------------------------------------------------------
        // Recette 6 : Burger de Steak
        Recipe recipe6 = new Recipe();
        recipe6.setTitle("Burger de Steak");
        recipe6.setDescription("Un burger gourmet à base de steak (cheval) et d'ingrédients savoureux.");
        recipe6.setPreparation_time(20);
        recipe6.setDifficulty(RecipeDifficulty.MEDIUM);
        recipe6.setCooking_time(10);
        recipe6.setServings_size(1);
        recipe6.setImage_file_name(null);
        recipe6.setLanguage("fr");
        recipe6.setDeleted(false);
        recipe6.setPublic(true);
        recipe6.setCreatedDate(LocalDate.now().minusDays(7));
        recipe6.setOwner(creator);

        Ingredient sauce_burger = new Ingredient();
        sauce_burger.setName("Sauce burger");
        sauce_burger.setDeleted(false);
        sauce_burger.setPublic(true);
        sauce_burger.setCreatedDate(LocalDate.now().minusDays(7));
        sauce_burger.setOwner(creator);
        sauce_burger.setImage_file_name(null);
        sauce_burger.setDescription(null);
        sauce_burger.setType(ingredientTypeRepository.getIngredientTypeById(10L));

        ingredientRepository.save(sauce_burger);

        List<RecipeIngredient> recipeIngredients6 = List.of(
                new RecipeIngredient(recipe6, pain_hamburger, 1.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe6, steak, 1.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe6, oignon, 0.5, QuantityType.UNIT, QuantityState.SLICED),
                new RecipeIngredient(recipe6, sauce_burger, 2.0, QuantityType.TBSP, null),
                new RecipeIngredient(recipe6, laitue, 1.0, QuantityType.LEAF, null),
                new RecipeIngredient(recipe6, tomate, 2.0, QuantityType.SLICE, null),
                new RecipeIngredient(recipe6, emmental, 30.0, QuantityType.G, null)
        );
        recipe6.setRecipeIngredients(recipeIngredients6);

        List<RecipeStep> recipeSteps6 = List.of(
                new RecipeStep(recipe6, 1, "Grillez le steak selon votre cuisson préférée."),
                new RecipeStep(recipe6, 2, "Toastez légèrement le pain hamburger."),
                new RecipeStep(recipe6, 3, "Montez le burger en superposant laitue, steak, tomates, oignons, fromage et sauce."),
                new RecipeStep(recipe6, 4, "Servez chaud accompagné de frites.")
        );
        recipe6.setRecipe_steps(recipeSteps6);
        recipe6.setType(main_course);
        recipe6.setTags(Set.of("burger", "steak", "fast-food", "rapide"));
        recipe6.setAuthor_note("Pour une version plus légère, utilisez un steak haché de volaille.");
        recipeRepository.save(recipe6);

        // -----------------------------------------------------------------------
        // Recette 7 : Gratin de courgettes
        Recipe recipe7 = new Recipe();
        recipe7.setTitle("Gratin de courgettes");
        recipe7.setDescription("Un gratin fondant à base de courgettes et de chevre.");
        recipe7.setPreparation_time(20);
        recipe7.setDifficulty(RecipeDifficulty.EASY);
        recipe7.setCooking_time(30);
        recipe7.setServings_size(4);
        recipe7.setImage_file_name(null);
        recipe7.setLanguage("fr");
        recipe7.setDeleted(false);
        recipe7.setPublic(true);
        recipe7.setCreatedDate(LocalDate.now().minusDays(4));
        recipe7.setOwner(creator);

        List<RecipeIngredient> recipeIngredients7 = List.of(
                new RecipeIngredient(recipe7, courgette, 3.0, QuantityType.UNIT, QuantityState.SLICED),
                new RecipeIngredient(recipe7, creme, 200.0, QuantityType.ML, null),
                new RecipeIngredient(recipe7, chevre, 200.0, QuantityType.G, null),
                new RecipeIngredient(recipe7, beurre, 20.0, QuantityType.G, null),
                new RecipeIngredient(recipe7, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe7, poivre, null, QuantityType.NONE, null)
        );
        recipe7.setRecipeIngredients(recipeIngredients7);

        List<RecipeStep> recipeSteps7 = List.of(
                new RecipeStep(recipe7, 1, "Préchauffez le four à 180°C."),
                new RecipeStep(recipe7, 2, "Disposez les courgettes en couches dans un plat à gratin."),
                new RecipeStep(recipe7, 3, "Versez la crème, salez, poivrez et parsemez de chevre."),
                new RecipeStep(recipe7, 4, "Enfournez pendant 30 minutes jusqu'à obtenir une belle croûte dorée.")
        );
        recipe7.setRecipe_steps(recipeSteps7);
        recipe7.setType(main_course);
        recipe7.setTags(Set.of("gratin", "courgette", "fromage", "vegetarien"));
        recipe7.setAuthor_note("Accompagnez ce plat d'une salade verte pour un repas équilibré.");
        recipeRepository.save(recipe7);

        // -----------------------------------------------------------------------
        // Recette 8 : Crêpes sucrées
        Recipe recipe8 = new Recipe();
        recipe8.setTitle("Crêpes sucrées");
        recipe8.setDescription("De délicieuses crêpes légères pour le petit déjeuner ou le dessert.");
        recipe8.setPreparation_time(10);
        recipe8.setDifficulty(RecipeDifficulty.EASY);
        recipe8.setCooking_time(15);
        recipe8.setServings_size(4);
        recipe8.setImage_file_name(null);
        recipe8.setLanguage("fr");
        recipe8.setDeleted(false);
        recipe8.setPublic(true);
        recipe8.setCreatedDate(LocalDate.now().minusDays(1));
        recipe8.setOwner(creator);

        List<RecipeIngredient> recipeIngredients8 = List.of(
                new RecipeIngredient(recipe8, farine, 200.0, QuantityType.G, null),
                new RecipeIngredient(recipe8, lait, 300.0, QuantityType.ML, null),
                new RecipeIngredient(recipe8, oeuf, 2.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe8, sucre, 30.0, QuantityType.G, null),
                new RecipeIngredient(recipe8, beurre, 20.0, QuantityType.G, QuantityState.MELTED),
                new RecipeIngredient(recipe8, vanille, 1.0, QuantityType.UNIT, null)
        );
        recipe8.setRecipeIngredients(recipeIngredients8);

        List<RecipeStep> recipeSteps8 = List.of(
                new RecipeStep(recipe8, 1, "Mélangez farine et sucre dans un saladier."),
                new RecipeStep(recipe8, 2, "Ajoutez les œufs puis le lait progressivement pour éviter les grumeaux."),
                new RecipeStep(recipe8, 3, "Incorporez le beurre fondu et la vanille."),
                new RecipeStep(recipe8, 4, "Laissez reposer la pâte pendant 30 minutes."),
                new RecipeStep(recipe8, 5, "Faites cuire les crêpes dans une poêle légèrement beurrée.")
        );
        recipe8.setRecipe_steps(recipeSteps8);
        recipe8.setType(dessert);
        recipe8.setTags(Set.of("crêpes", "sucré", "dessert", "petit déjeuner"));
        recipe8.setAuthor_note("Servez avec du sirop d'érable, du miel ou de la confiture.");
        recipeRepository.save(recipe8);

        // -----------------------------------------------------------------------
        // Recette 9 : Risotto aux champignons
        Recipe recipe9 = new Recipe();
        recipe9.setTitle("Risotto aux champignons");
        recipe9.setDescription("Un risotto crémeux et parfumé aux champignons et parmesan.");
        recipe9.setPreparation_time(10);
        recipe9.setDifficulty(RecipeDifficulty.MEDIUM);
        recipe9.setCooking_time(25);
        recipe9.setServings_size(3);
        recipe9.setImage_file_name(null);
        recipe9.setLanguage("fr");
        recipe9.setDeleted(false);
        recipe9.setPublic(true);
        recipe9.setCreatedDate(LocalDate.now().minusDays(6));
        recipe9.setOwner(creator);

        List<RecipeIngredient> recipeIngredients9 = List.of(
                new RecipeIngredient(recipe9, riz_arborio, 300.0, QuantityType.G, null),
                new RecipeIngredient(recipe9, champignon, 200.0, QuantityType.G, QuantityState.SLICED),
                new RecipeIngredient(recipe9, oignon, 1.0, QuantityType.UNIT, QuantityState.DICED),
                new RecipeIngredient(recipe9, vin_blanc, 100.0, QuantityType.ML, null),
                new RecipeIngredient(recipe9, bouillon, 1.0, QuantityType.L, null),
                new RecipeIngredient(recipe9, parmesan, 50.0, QuantityType.G, QuantityState.GRATED),
                new RecipeIngredient(recipe9, beurre, 30.0, QuantityType.G, null),
                new RecipeIngredient(recipe9, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe9, poivre, null, QuantityType.NONE, null)
        );
        recipe9.setRecipeIngredients(recipeIngredients9);

        List<RecipeStep> recipeSteps9 = List.of(
                new RecipeStep(recipe9, 1, "Faites revenir l'oignon dans le beurre jusqu'à ce qu'il soit translucide."),
                new RecipeStep(recipe9, 2, "Ajoutez le riz et faites-le nacrer pendant 2 minutes."),
                new RecipeStep(recipe9, 3, "Déglacez avec le vin blanc et laissez réduire."),
                new RecipeStep(recipe9, 4, "Incorporez les champignons puis ajoutez progressivement le bouillon chaud."),
                new RecipeStep(recipe9, 5, "Quand le riz est cuit, ajoutez le parmesan, salez, poivrez et servez immédiatement.")
        );
        recipe9.setRecipe_steps(recipeSteps9);
        recipe9.setType(main_course);
        recipe9.setTags(Set.of("risotto", "champignons", "italien", "crémeux"));
        recipe9.setAuthor_note("Remuez constamment pour obtenir un risotto onctueux.");
        recipeRepository.save(recipe9);

        // -----------------------------------------------------------------------
        // Recette 10 : Tarte aux pommes
        Recipe recipe10 = new Recipe();
        recipe10.setTitle("Tarte aux pommes");
        recipe10.setDescription("Une tarte traditionnelle aux pommes parfumée à la cannelle.");
        recipe10.setPreparation_time(30);
        recipe10.setDifficulty(RecipeDifficulty.MEDIUM);
        recipe10.setCooking_time(45);
        recipe10.setServings_size(6);
        recipe10.setImage_file_name(null);
        recipe10.setLanguage("fr");
        recipe10.setDeleted(false);
        recipe10.setPublic(true);
        recipe10.setCreatedDate(LocalDate.now().minusDays(8));
        recipe10.setOwner(creator);

        List<RecipeIngredient> recipeIngredients10 = List.of(
                new RecipeIngredient(recipe10, farine, 300.0, QuantityType.G, null),
                new RecipeIngredient(recipe10, beurre, 150.0, QuantityType.G, null),
                new RecipeIngredient(recipe10, sucre, 100.0, QuantityType.G, null),
                new RecipeIngredient(recipe10, oeuf, 1.0, QuantityType.UNIT, null),
                new RecipeIngredient(recipe10, pomme, 4.0, QuantityType.UNIT, QuantityState.SLICED),
                new RecipeIngredient(recipe10, cannelle, 1.0, QuantityType.TSP, null),
                new RecipeIngredient(recipe10, lait, 50.0, QuantityType.ML, null)
        );
        recipe10.setRecipeIngredients(recipeIngredients10);

        List<RecipeStep> recipeSteps10 = List.of(
                new RecipeStep(recipe10, 1, "Préparez la pâte en mélangeant farine, beurre, sucre et œuf."),
                new RecipeStep(recipe10, 2, "Laissez reposer la pâte au réfrigérateur pendant 30 minutes."),
                new RecipeStep(recipe10, 3, "Étalez la pâte dans un moule et disposez les tranches de pomme en rosace."),
                new RecipeStep(recipe10, 4, "Saupoudrez de cannelle et arrosez de lait."),
                new RecipeStep(recipe10, 5, "Enfournez à 180°C pendant 45 minutes jusqu'à dorure.")
        );
        recipe10.setRecipe_steps(recipeSteps10);
        recipe10.setType(dessert);
        recipe10.setTags(Set.of("tarte", "pomme", "dessert", "traditionnel"));
        recipe10.setAuthor_note("À déguster tiède, éventuellement accompagnée d'une boule de glace à la vanille.");
        recipeRepository.save(recipe10);

        // -----------------------------------------------------------------------
// Recette 11 : Filet mignon de porc aux haricots et pommes de terre
        Recipe recipe11 = new Recipe();
        recipe11.setTitle("Filet mignon de porc aux haricots et pommes de terre");
        recipe11.setDescription("Recette complexe et savoureuse combinant un filet mignon de porc tendre, des haricots verts croquants et des pommes de terre fondantes, le tout relevé d'une sauce gourmande.");
        recipe11.setPreparation_time(40);
        recipe11.setDifficulty(RecipeDifficulty.HARD);
        recipe11.setCooking_time(60);
        recipe11.setServings_size(4);
        recipe11.setImage_file_name(null);
        recipe11.setLanguage("fr");
        recipe11.setDeleted(false);
        recipe11.setPublic(true);
        recipe11.setCreatedDate(LocalDate.now().minusDays(1));
        recipe11.setOwner(creator);

        List<RecipeIngredient> recipeIngredients11 = List.of(
                new RecipeIngredient(recipe11, filet_mignon_porc, 800.0, QuantityType.G, null),
                new RecipeIngredient(recipe11, haricots, 300.0, QuantityType.G, null),
                new RecipeIngredient(recipe11, pomme_de_terre, 500.0, QuantityType.G, QuantityState.DICED),
                new RecipeIngredient(recipe11, oignon, 1.0, QuantityType.UNIT, QuantityState.DICED),
                new RecipeIngredient(recipe11, ail, 2.0, QuantityType.CLOVE, QuantityState.PEELED),
                new RecipeIngredient(recipe11, vin_blanc, 150.0, QuantityType.ML, null),
                new RecipeIngredient(recipe11, bouillon, 500.0, QuantityType.ML, null),
                new RecipeIngredient(recipe11, herbe_de_provence, 1.0, QuantityType.TSP, null),
                new RecipeIngredient(recipe11, huile_olive, 2.0, QuantityType.TBSP, null),
                new RecipeIngredient(recipe11, sel, null, QuantityType.NONE, null),
                new RecipeIngredient(recipe11, poivre, null, QuantityType.NONE, null)
        );
        recipe11.setRecipeIngredients(recipeIngredients11);

        List<RecipeStep> recipeSteps11 = List.of(
                new RecipeStep(recipe11, 1, "Assaisonnez le filet mignon de porc avec du sel, du poivre et les herbes de Provence. Faites-le dorer dans une poêle avec l'huile d'olive."),
                new RecipeStep(recipe11, 2, "Réservez la viande. Dans la même poêle, faites revenir l'oignon et l'ail jusqu'à ce qu'ils deviennent translucides."),
                new RecipeStep(recipe11, 3, "Déglacez la poêle avec le vin blanc, puis ajoutez le bouillon. Laissez réduire quelques minutes pour concentrer les saveurs."),
                new RecipeStep(recipe11, 4, "Ajoutez les pommes de terre coupées en dés et laissez mijoter à feu moyen pendant environ 15 minutes."),
                new RecipeStep(recipe11, 5, "Incorporez les haricots verts et poursuivez la cuisson pendant 10 minutes supplémentaires."),
                new RecipeStep(recipe11, 6, "Réintroduisez le filet mignon, découpez-le en tranches épaisses et laissez mijoter encore 5 minutes pour amalgamer les saveurs."),
                new RecipeStep(recipe11, 7, "Rectifiez l'assaisonnement avec du sel et du poivre, puis servez chaud.")
        );
        recipe11.setRecipe_steps(recipeSteps11);

        recipe11.setType(main_course);
        recipe11.setTags(Set.of("porc", "haricots", "pommes de terre", "complexe", "gourmand"));
        recipe11.setAuthor_note("Une recette complexe nécessitant une bonne gestion du temps pour obtenir une viande tendre et une sauce onctueuse.");
        recipeRepository.save(recipe11);
    }

    @Override
    public void run(String... args) throws Exception {
        seedRecipesTypes();
        seedRecipes();
    }
}
