package graber.thomas.feastverse.seed;

import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IngredientSeeder implements CommandLineRunner {
    private IngredientRepository ingredientRepository;
    private IngredientTypeRepository ingredientTypeRepository;

    public IngredientSeeder(IngredientRepository ingredientRepository, IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    private void seedIngredientType(){
        IngredientType vegetables = new IngredientType();
        vegetables.setId(1L);
        vegetables.setName("Vegetables");
        vegetables.setDescription("Rich in vitamins and fiber, vegetables are grouped into different categories. Carrots, turnips and beets for root vegetables, green or red cabbage, spinach, lamb's lettuce for leafy vegetables, cauliflower and artichoke for flower vegetables, tomatoes and avocado for fruit vegetables, and finally lentils, dried beans and all legumes for dried vegetables.");
        vegetables.setImage_file_name("vegetables.jpg");
        ingredientTypeRepository.save(vegetables);

        IngredientType fruits = new IngredientType();
        fruits.setId(2L);
        fruits.setName("Fruits");
        fruits.setDescription("Essential because they are rich in nutrients, fruits are divided into several groups. Oranges, lemons and grapefruit belong to the citrus family, apples and pears are pome fruits while apricots and peaches are stone fruits. We find berries like blackcurrant or nuts like walnuts or almonds. Pineapple, passion fruit or mango are considered exotic fruits.");
        fruits.setImage_file_name("fruits.jpg");
        ingredientTypeRepository.save(fruits);

        IngredientType meat = new IngredientType();
        meat.setId(3L);
        meat.setName("Meats, poultry and delicatessen products");
        meat.setDescription("Beef (rib steak, flank steak) is considered red meat while veal (shank or brisket) and pork (filet mignon or loin) are considered white meat. Chicken, turkey, and capon are poultry, as is... rabbit! There are also game meats (ostrich, deer) and lesser-consumed meats such as horse. White offal such as tripe and sweetbreads or red offal such as liver can be delicacies. As for charcuterie, there is salami and sausages (from Toulouse, Strasbourg), white or raw ham, and bacon.");
        meat.setImage_file_name("meat.jpg");
        ingredientTypeRepository.save(meat);

        IngredientType fish = new IngredientType();
        fish.setId(4L);
        fish.setName("Fish and seafood");
        fish.setDescription("Sources of valuable trace elements, seafood is divided into several groups: sea fish such as sea bass and cod, freshwater fish such as eel and pike, crustaceans such as shrimp or Norway lobster and molluscs such as oyster, mussels or scallops. Poached or grilled, steamed or cassoiled, or even left raw, these fish and seafood are cooked by all sauces.");
        fish.setImage_file_name("fish.jpg");
        ingredientTypeRepository.save(fish);

        IngredientType eggs = new IngredientType();
        eggs.setId(5L);
        eggs.setName("Eggs, cheeses and dairy products");
        eggs.setDescription("If milk is a source of calcium, yogurts and cottage cheeses are too, just like cheese. On one side we find fresh cheeses like ricotta or brocciu, soft cheeses like Camembert or Brie, blue cheeses like Roquefort and pressed cheeses like Reblochon. Finally, to stock up on protein, chicken or quail eggs are a wonderful replacement for meat.If milk is a source of calcium, yogurts and white cheeses are also, just like cheese. On the one hand, there are fresh cheeses like ricotta or brocciu, soft cheeses such as camembert or brie, cheeses parsley like Roquefort and pressed cheeses like reblochon. Finally, to make protein reserves, chicken or quail eggs replace the meat perfectly.");
        eggs.setImage_file_name("eggs.jpg");
        ingredientTypeRepository.save(eggs);

        IngredientType pasta = new IngredientType();
        pasta.setId(6L);
        pasta.setName("Pasta, rice, seeds, cereals and breads");
        pasta.setDescription("Farfalle , penne rigate, spaghetti, macaroni: pasta is now a must in French cuisine. But cereals such as rice (white or whole), spelt , quinoa and corn in the form of polenta are becoming increasingly popular. Let's not forget that cereals are the source of flours that can be used to make bread (sandwich, country). In another register, sunflower , flax or sesame seeds add a crunchy touch to preparations.\n");
        pasta.setImage_file_name("pasta.jpg");
        ingredientTypeRepository.save(pasta);

        IngredientType spices = new IngredientType();
        spices.setId(7L);
        spices.setName("Spices, oils and condiments");
        spices.setDescription("Flavor enhancers par excellence, spices flavor both sweet and savory dishes. Derived from flowers ( saffron , cloves) or plant seeds ( poppy and fennel seeds), bark ( cinnamon ) or fruits (pepper, sumac ), rhizomes ( ginger , turmeric) or fruit stones ( nutmeg ), spices can also be mixtures ( curry , ras-el-hanout, five berries, etc.). Aromatic herbs such as basil and sage as well as condiments such as mustard or Tabasco also enhance the taste of food. As for oils, some can be fried (sunflower oil) and others not (olive oil). Walnut and sesame oils are perfect for seasoning a salad.\n");
        spices.setImage_file_name("spices.jpg");
        ingredientTypeRepository.save(spices);

        IngredientType chocolate = new IngredientType();
        chocolate.setId(8L);
        chocolate.setName("Chocolates and sweet products");
        chocolate.setDescription("What would baking be without honey, chocolate and caramel? To give a floral note to your cakes and a caramelized side to your roast pork, honey is ideal. It can be replaced with agave syrup or maple syrup. But for an ultra-gourmet touch, chocolate (dark, milk or white) is a must and comes in the form of ganaches, sauces, shavings, etc. Finally, caramel (with salted butter!) and jam coat both toast and pancakes, in addition to garnishing or accompanying sponge cakes.");
        chocolate.setImage_file_name("chocolate.jpg");
        ingredientTypeRepository.save(chocolate);

        IngredientType drinks = new IngredientType();
        drinks.setId(9L);
        drinks.setName("Drinks");
        drinks.setDescription("The drink is the other central element of a breakfast, dinner or aperitif. There are non-alcoholic drinks on one side (tea, coffee, fruit juice) and alcoholic drinks on the other. Some are made from cereals like whisky and beer, others from plants like rum and gin, and some are made from fruits (wine, cider).");
        drinks.setImage_file_name("drinks.jpg");
        ingredientTypeRepository.save(drinks);
    }

    @Override
    public void run(String... args) {
        seedIngredientType();
    }
}
