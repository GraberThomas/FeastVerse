package graber.thomas.feastverse.seed;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientSeeder implements CommandLineRunner {
    private final IngredientRepository ingredientRepository;
    private final IngredientTypeRepository ingredientTypeRepository;
    private static final Logger logger = LoggerFactory.getLogger(IngredientSeeder.class);

    public IngredientSeeder(IngredientRepository ingredientRepository, IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    private void seedIngredientType(){
        List<IngredientType> ingredientsTypes= List.of(
                new IngredientType(
                        1L,
                        "Vegetables",
                        "Rich in vitamins and fiber, vegetables are grouped into different categories. Carrots, turnips and beets for root vegetables, green or red cabbage, spinach, lamb's lettuce for leafy vegetables, cauliflower and artichoke for flower vegetables, tomatoes and avocado for fruit vegetables, and finally lentils, dried beans and all legumes for dried vegetables.",
                        "vegetables.jpg"
                ),
                new IngredientType(
                        2L,
                        "Fruits",
                        "Essential because they are rich in nutrients, fruits are divided into several groups. Oranges, lemons and grapefruit belong to the citrus family, apples and pears are pome fruits while apricots and peaches are stone fruits. We find berries like blackcurrant or nuts like walnuts or almonds. Pineapple, passion fruit or mango are considered exotic fruits.",
                        "fruits.jpg"
                ),
                new IngredientType(
                        3L,
                        "Meats, poultry and delicatessen products",
                        "Beef (rib steak, flank steak) is considered red meat while veal (shank or brisket) and pork (filet mignon or loin) are considered white meat. Chicken, turkey, and capon are poultry, as is... rabbit! There are also game meats (ostrich, deer) and lesser-consumed meats such as horse. White offal such as tripe and sweetbreads or red offal such as liver can be delicacies. As for charcuterie, there is salami and sausages (from Toulouse, Strasbourg), white or raw ham, and bacon.",
                        "meat.jpg"
                ),
                new IngredientType(
                        4L,
                        "Fish and seafood",
                        "Sources of valuable trace elements, seafood is divided into several groups: sea fish such as sea bass and cod, freshwater fish such as eel and pike, crustaceans such as shrimp or Norway lobster and molluscs such as oyster, mussels or scallops. Poached or grilled, steamed or cassoiled, or even left raw, these fish and seafood are cooked by all sauces.",
                        "fish.jpg"
                ),
                new IngredientType(
                        5L,
                        "Eggs, cheeses and dairy products",
                        "If milk is a source of calcium, yogurts and cottage cheeses are too, just like cheese. On one side we find fresh cheeses like ricotta or brocciu, soft cheeses like Camembert or Brie, blue cheeses like Roquefort and pressed cheeses like Reblochon. Finally, to stock up on protein, chicken or quail eggs are a wonderful replacement for meat.If milk is a source of calcium, yogurts and white cheeses are also, just like cheese. On the one hand, there are fresh cheeses like ricotta or brocciu, soft cheeses such as camembert or brie, cheeses parsley like Roquefort and pressed cheeses like reblochon. Finally, to make protein reserves, chicken or quail eggs replace the meat perfectly.",
                        "eggs.jpg"
                ),
                new IngredientType(
                        6L,
                        "Pasta, rice, seeds, cereals and breads",
                        "Farfalle , penne rigate, spaghetti, macaroni: pasta is now a must in French cuisine. But cereals such as rice (white or whole), spelt , quinoa and corn in the form of polenta are becoming increasingly popular. Let's not forget that cereals are the source of flours that can be used to make bread (sandwich, country). In another register, sunflower , flax or sesame seeds add a crunchy touch to preparations.",
                        "pasta.jpg"
                ),
                new IngredientType(
                        7L,
                        "Spices, oils and condiments",
                        "Flavor enhancers par excellence, spices flavor both sweet and savory dishes. Derived from flowers ( saffron , cloves) or plant seeds ( poppy and fennel seeds), bark ( cinnamon ) or fruits (pepper, sumac ), rhizomes ( ginger , turmeric) or fruit stones ( nutmeg ), spices can also be mixtures ( curry , ras-el-hanout, five berries, etc.). Aromatic herbs such as basil and sage as well as condiments such as mustard or Tabasco also enhance the taste of food. As for oils, some can be fried (sunflower oil) and others not (olive oil). Walnut and sesame oils are perfect for seasoning a salad.",
                        "spices.jpg"
                ),
                new IngredientType(
                        8L,
                        "Chocolates and sweet products",
                        "What would baking be without honey, chocolate and caramel? To give a floral note to your cakes and a caramelized side to your roast pork, honey is ideal. It can be replaced with agave syrup or maple syrup. But for an ultra-gourmet touch, chocolate (dark, milk or white) is a must and comes in the form of ganaches, sauces, shavings, etc. Finally, caramel (with salted butter!) and jam coat both toast and pancakes, in addition to garnishing or accompanying sponge cakes.",
                        "chocolate.jpg"
                ),
                new IngredientType(
                        9L,
                        "Drinks",
                        "The drink is the other central element of a breakfast, dinner or aperitif. There are non-alcoholic drinks on one side (tea, coffee, fruit juice) and alcoholic drinks on the other. Some are made from cereals like whisky and beer, others from plants like rum and gin, and some are made from fruits (wine, cider).",
                        "drinks.jpg"
                )
        );

        ingredientTypeRepository.saveAll(ingredientsTypes);
    }

    private void seedIngredient(){
        IngredientType VEGETABLES = ingredientTypeRepository.getIngredientTypeById(1L);
        IngredientType FRUITS = ingredientTypeRepository.getIngredientTypeById(2L);
        IngredientType MEAT = ingredientTypeRepository.getIngredientTypeById(3L);
        IngredientType FISH = ingredientTypeRepository.getIngredientTypeById(4L);
        IngredientType EGGS = ingredientTypeRepository.getIngredientTypeById(5L);
        IngredientType PASTA = ingredientTypeRepository.getIngredientTypeById(6L);
        IngredientType SPICES = ingredientTypeRepository.getIngredientTypeById(7L);
        IngredientType CHOCOLATES = ingredientTypeRepository.getIngredientTypeById(8L);
        IngredientType DRINKS = ingredientTypeRepository.getIngredientTypeById(9L);

        List<Ingredient> vegetables_ingredients = parseCsv("vegetables.csv", VEGETABLES);
        List<Ingredient> fruits_ingredients = parseCsv("fruits.csv", FRUITS);
        List<Ingredient> meat_ingredients = parseCsv("meats__poultry_and_delicatessen_products.csv", MEAT);
        List<Ingredient> fish_ingredients = parseCsv("fish_and_seafood.csv", FISH);
        List<Ingredient> eggs_ingredients = parseCsv("eggs__cheeses_and_dairy_products.csv", EGGS);
        List<Ingredient> pasta_ingredients = parseCsv("pasta__rice__seeds__cereals_and_breads.csv", PASTA);
        List<Ingredient> spices_ingredients = parseCsv("spices__oils_and_condiments.csv", SPICES);
        List<Ingredient> chocolates_ingredients = parseCsv("chocolates_and_sweet_products.csv", CHOCOLATES);
        List<Ingredient> drinks_ingredients = parseCsv("drinks.csv", DRINKS);

        this.ingredientRepository.saveAll(vegetables_ingredients);
        this.ingredientRepository.saveAll(fruits_ingredients);
        this.ingredientRepository.saveAll(meat_ingredients);
        this.ingredientRepository.saveAll(fish_ingredients);
        this.ingredientRepository.saveAll(eggs_ingredients);
        this.ingredientRepository.saveAll(pasta_ingredients);
        this.ingredientRepository.saveAll(spices_ingredients);
        this.ingredientRepository.saveAll(chocolates_ingredients);
        this.ingredientRepository.saveAll(drinks_ingredients);
    }

    private List<Ingredient> parseCsv(String fileName, IngredientType ingredientType) {
        List<Ingredient> ingredients = new ArrayList<>();

        try {
            // Charger le fichier depuis le dossier resources/csv
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("csv/" + fileName);

            if (inputStream == null) {
                throw new IllegalArgumentException("Fichier introuvable : " + fileName);
            }

            // Lire le fichier avec encodage UTF-8
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            // Utiliser Apache Commons CSV pour parser le contenu
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withDelimiter(';')  // Définir ";" comme séparateur
                    .withHeader("URL", "Titre", "Description", "ImageURL") // En-têtes du fichier CSV
                    .withSkipHeaderRecord(true) // Ignorer la première ligne (les en-têtes)
                    .parse(reader);

            for (CSVRecord record : records) {
                String title = record.get("Titre");
                String description = record.get("Description");
                String imageUrl = record.get("ImageURL");

                // Créer un nouvel objet Ingredient à partir des données
                Ingredient ingredient = new Ingredient();
                ingredient.setName(title);
                ingredient.setDescription(description);
                ingredient.setImage_file_name(imageUrl);
                ingredient.setType(ingredientType);
                ingredients.add(ingredient);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ingredients;
    }

    @Override
    public void run(String... args) {
        seedIngredientType();
        seedIngredient();
    }
}
