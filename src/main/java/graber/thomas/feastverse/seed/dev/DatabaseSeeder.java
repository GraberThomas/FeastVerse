package graber.thomas.feastverse.seed.dev;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import graber.thomas.feastverse.repository.report.ReportRepository;
import graber.thomas.feastverse.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

/**
 * The DatabaseSeeder class is a Spring component responsible for seeding the database
 * with initial user data specifically for the development environment.
 * <p>
 * This class implements the CommandLineRunner interface and is executed automatically
 * at application startup when the "dev" profile is active. Its primary role is to create
 * and populate the database with pre-defined users if they do not already exist.
 * <p>
 * The seeding process includes creating various user roles such as administrator, moderator,
 * and standard users. The users' information, including email, pseudo, password, roles, and
 * other metadata, is provided within the class.
 * <p>
 * An encoded password is generated for each user using a PasswordEncoder to ensure secure
 * password storage.
 *<p>
 * An initial administrator account with elevated privileges is also created for
 * administrative purposes.
 *<p>
 * Dependencies:
 * - UserRepository: Used for accessing and manipulating user data.
 * - PasswordEncoder: Utilized for encoding user passwords.
 */
@Component
@Profile("dev")
@Order(2)
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final PasswordEncoder encoder;
    private final IngredientTypeRepository ingredientTypeRepository;
    private final IngredientRepository ingredientRepository;

    public DatabaseSeeder(UserRepository userRepository, ReportRepository reportRepository, PasswordEncoder encoder, IngredientTypeRepository ingredientTypeRepository, IngredientRepository ingredientRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) {
        if(userRepository.findUserByEmail("admin@feastverse.com") == null) {
            User admin = new User();
            admin.setEmail("admin@feastverse.com");
            admin.setPseudo("admin");
            admin.setPassword(encoder.encode("oH!862u.O@pS2wb£"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setRoles(Set.of(UserType.ADMINISTRATOR));
            admin.setCreatedDate(LocalDate.now());
            admin.setUpdatedDate(LocalDate.now());
            this.userRepository.save(admin);

            User testUser = new User();
            testUser.setEmail("asouchon_fake@gmail.com");
            testUser.setPseudo("asouchon");
            testUser.setPassword(encoder.encode("TestUser1!"));
            testUser.setFirstName("Alain");
            testUser.setLastName("Souchon");
            testUser.setRoles(Set.of(UserType.STANDARD));
            testUser.setCreatedDate(LocalDate.now());
            testUser.setUpdatedDate(LocalDate.now());
            this.userRepository.save(testUser);

            User testUser2 = new User();
            testUser2.setEmail("pcruel_fake@gmail.com");
            testUser2.setPseudo("pcruel");
            testUser2.setPassword(encoder.encode("TestUser2!"));
            testUser2.setFirstName("Patrick");
            testUser2.setLastName("Cruel");
            testUser2.setRoles(Set.of(UserType.STANDARD));
            testUser2.setCreatedDate(LocalDate.now());
            testUser2.setUpdatedDate(LocalDate.now());
            this.userRepository.save(testUser2);

            User testUser3 = new User();
            testUser3.setEmail("emacron@gmail.com");
            testUser3.setPseudo("emacron");
            testUser3.setPassword(encoder.encode("TestUser3!"));
            testUser3.setFirstName("Emmanuel");
            testUser3.setLastName("Macron");
            testUser3.setRoles(Set.of(UserType.STANDARD, UserType.MODERATOR));
            testUser3.setCreatedDate(LocalDate.now());
            testUser3.setUpdatedDate(LocalDate.now());
            this.userRepository.save(testUser3);

            User mod1 = new User();
            mod1.setEmail("vincent.tastique@feastverse.com");
            mod1.setPseudo("vincent.tastique");
            mod1.setPassword(encoder.encode("ModUser1!"));
            mod1.setFirstName("Vincent");
            mod1.setLastName("Tastique");
            mod1.setRoles(Set.of(UserType.STANDARD, UserType.MODERATOR));
            mod1.setCreatedDate(LocalDate.now());
            mod1.setUpdatedDate(LocalDate.now());
            this.userRepository.save(mod1);

            User mod2 = new User();
            mod2.setEmail("hugo.gourmand@feastverse.com");
            mod2.setPseudo("hugo.gourmand");
            mod2.setPassword(encoder.encode("ModUser2!"));
            mod2.setFirstName("Hugo");
            mod2.setLastName("Gourmand");
            mod2.setRoles(Set.of(UserType.STANDARD, UserType.MODERATOR));
            mod2.setCreatedDate(LocalDate.now());
            mod2.setUpdatedDate(LocalDate.now());
            this.userRepository.save(mod2);

            User user1 = new User();
            user1.setEmail("paul.lent@feastverse.com");
            user1.setPseudo("paul.lent");
            user1.setPassword(encoder.encode("Standard1!"));
            user1.setFirstName("Paul");
            user1.setLastName("Lent");
            user1.setRoles(Set.of(UserType.STANDARD));
            user1.setCreatedDate(LocalDate.now());
            user1.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user1);

            Report report = new Report();
            report.setTarget(user1);
            report.setReporter(testUser2);
            report.setMessage("This user has inappropriate comments!");
            report.setType(ReportType.INAPPROPRIATE_CONTENT);
            report.setCreatedDate(LocalDate.of(2024, 12, 10));
            report.setResolved(true);
            this.reportRepository.save(report);

            User user2 = new User();
            user2.setEmail("claire.ment@feastverse.com");
            user2.setPseudo("claire.ment");
            user2.setPassword(encoder.encode("Standard2!"));
            user2.setFirstName("Claire");
            user2.setLastName("Ment");
            user2.setRoles(Set.of(UserType.STANDARD));
            user2.setCreatedDate(LocalDate.now());
            user2.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user2);

            User user3 = new User();
            user3.setEmail("ella.gantine@feastverse.com");
            user3.setPseudo("ella.gantine");
            user3.setPassword(encoder.encode("Standard3!"));
            user3.setFirstName("Ella");
            user3.setLastName("Gantine");
            user3.setRoles(Set.of(UserType.STANDARD));
            user3.setCreatedDate(LocalDate.now());
            user3.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user3);

            Report report2 = new Report();
            report2.setTarget(user3);
            report2.setReporter(testUser2);
            report2.setMessage("This user is racist!");
            report2.setType(ReportType.HATE_SPEECH);
            report2.setCreatedDate(LocalDate.of(2025, 1, 8));
            this.reportRepository.save(report2);

            Report report3 = new Report();
            report3.setTarget(user2);
            report3.setReporter(user3);
            report3.setType(ReportType.MISINFORMATION);
            report3.setCreatedDate(LocalDate.of(2025, 1, 10));
            this.reportRepository.save(report3);


            User user4 = new User();
            user4.setEmail("guy.tare@feastverse.com");
            user4.setPseudo("guy.tare");
            user4.setPassword(encoder.encode("Standard4!"));
            user4.setFirstName("Guy");
            user4.setLastName("Tare");
            user4.setRoles(Set.of(UserType.STANDARD));
            user4.setCreatedDate(LocalDate.now());
            user4.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user4);

            User user5 = new User();
            user5.setEmail("anne.anas@feastverse.com");
            user5.setPseudo("anne.anas");
            user5.setPassword(encoder.encode("Standard5!"));
            user5.setFirstName("Anne");
            user5.setLastName("Anas");
            user5.setRoles(Set.of(UserType.STANDARD));
            user5.setCreatedDate(LocalDate.now());
            user5.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user5);

            User user6 = new User();
            user6.setEmail("julie.enn@feastverse.com");
            user6.setPseudo("julie.enn");
            user6.setPassword(encoder.encode("Standard6!"));
            user6.setFirstName("Julie");
            user6.setLastName("Enn");
            user6.setRoles(Set.of(UserType.STANDARD));
            user6.setCreatedDate(LocalDate.now());
            user6.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user6);

            User user7 = new User();
            user7.setEmail("leo.nardo@feastverse.com");
            user7.setPseudo("leo.nardo");
            user7.setPassword(encoder.encode("Standard7!"));
            user7.setFirstName("Leo");
            user7.setLastName("Nardo");
            user7.setRoles(Set.of(UserType.STANDARD));
            user7.setCreatedDate(LocalDate.now());
            user7.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user7);

            User user8 = new User();
            user8.setEmail("ade.le@feastverse.com");
            user8.setPseudo("ade.le");
            user8.setPassword(encoder.encode("Standard8!"));
            user8.setFirstName("Adele");
            user8.setLastName("Le");
            user8.setRoles(Set.of(UserType.STANDARD));
            user8.setCreatedDate(LocalDate.now());
            user8.setUpdatedDate(LocalDate.now());
            this.userRepository.save(user8);

            Report report4 = new Report();
            report4.setTarget(user7);
            report4.setReporter(user8);
            report4.setMessage("Post only protected recipes");
            report4.setType(ReportType.COPYRIGHT_VIOLATION);
            report4.setCreatedDate(LocalDate.of(2024, 10, 3));
            this.reportRepository.save(report4);

            IngredientType VEGETABLES = ingredientTypeRepository.getIngredientTypeById(1L);
            IngredientType FRUITS = ingredientTypeRepository.getIngredientTypeById(2L);
            IngredientType MEAT = ingredientTypeRepository.getIngredientTypeById(3L);
            IngredientType FISH = ingredientTypeRepository.getIngredientTypeById(4L);
            IngredientType EGGS = ingredientTypeRepository.getIngredientTypeById(5L);
            IngredientType PASTA = ingredientTypeRepository.getIngredientTypeById(6L);
            IngredientType SPICES = ingredientTypeRepository.getIngredientTypeById(7L);
            IngredientType CHOCOLATES = ingredientTypeRepository.getIngredientTypeById(8L);
            IngredientType DRINKS = ingredientTypeRepository.getIngredientTypeById(9L);

            Ingredient testIngredient = new Ingredient(
                    "M&Ms",
                    CHOCOLATES,
                    null,
                    "Mauvais pour la santé"
            );
            testIngredient.setOwner(user7);
            testIngredient.setPublic(true);
            testIngredient.setDeleted(false);
            ingredientRepository.save(testIngredient);

            Ingredient ingredient1 = new Ingredient("Carrot Red", VEGETABLES, null, "Fresh and crunchy carrot");
            ingredient1.setOwner(user1);
            ingredient1.setPublic(true);
            ingredient1.setDeleted(false);
            ingredient1.setCreatedDate(LocalDate.of(2023,10,10));
            ingredientRepository.save(ingredient1);

            Ingredient ingredient2 = new Ingredient("Tomato blue", VEGETABLES, null, "Ripe and juicy tomato");
            ingredient2.setOwner(user2);
            ingredient2.setPublic(false);
            ingredient2.setDeleted(false);
            ingredient2.setCreatedDate(LocalDate.of(2024,2,14));
            ingredient2.setUpdatedDate(LocalDate.now());
            ingredientRepository.save(ingredient2);

            Ingredient ingredient3 = new Ingredient("Banana meat", FRUITS, null, "Sweet and healthy banana");
            ingredient3.setOwner(user3);
            ingredient3.setPublic(true);
            ingredient3.setDeleted(false);
            ingredient3.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient3);

            Ingredient ingredient4 = new Ingredient("Chicken Breast", MEAT, null, "Lean and protein-packed chicken");
            ingredient4.setOwner(user4);
            ingredient4.setPublic(false);
            ingredient4.setDeleted(false);
            ingredient4.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient4);

            Ingredient ingredient5 = new Ingredient("Salmon Fillet", FISH, null, "Fresh Atlantic salmon");
            ingredient5.setOwner(user5);
            ingredient5.setPublic(true);
            ingredient5.setDeleted(false);
            ingredient5.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient5);

            Ingredient ingredient6 = new Ingredient("Eggs Rooten", EGGS, null, "Organic free-range eggs");
            ingredient6.setOwner(user6);
            ingredient6.setPublic(false);
            ingredient6.setDeleted(false);
            ingredient6.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient6);

            Ingredient ingredient7 = new Ingredient("Penne Pasta", PASTA, null, "Italian-style penne pasta");
            ingredient7.setOwner(user7);
            ingredient7.setPublic(true);
            ingredient7.setDeleted(false);
            ingredient7.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient7);

            Ingredient ingredient8 = new Ingredient("Black Pepper", SPICES, null, "Finely ground black pepper");
            ingredient8.setOwner(user8);
            ingredient8.setPublic(true);
            ingredient8.setDeleted(true);
            ingredient8.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient8);

            Ingredient ingredient9 = new Ingredient("Dark Chocolate", CHOCOLATES, null, "70% cocoa dark chocolate");
            ingredient9.setOwner(user7);
            ingredient9.setPublic(false);
            ingredient9.setDeleted(false);
            ingredient9.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient9);

            Ingredient ingredient10 = new Ingredient("Orange Juice", DRINKS, null, "Freshly squeezed orange juice");
            ingredient10.setOwner(user3);
            ingredient10.setPublic(true);
            ingredient10.setDeleted(false);
            ingredient10.setCreatedDate(LocalDate.of(2024,2,14));
            ingredientRepository.save(ingredient10);
        }
    }
}
