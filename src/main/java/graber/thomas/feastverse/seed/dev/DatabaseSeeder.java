package graber.thomas.feastverse.seed.dev;

import graber.thomas.feastverse.model.User;
import graber.thomas.feastverse.model.UserType;
import graber.thomas.feastverse.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
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

            testUser = new User();
            testUser.setEmail("pcruel_fake@gmail.com");
            testUser.setPseudo("pcruel");
            testUser.setPassword(encoder.encode("TestUser2!"));
            testUser.setFirstName("Patrick");
            testUser.setLastName("Cruel");
            testUser.setRoles(Set.of(UserType.STANDARD));
            testUser.setCreatedDate(LocalDate.now());
            testUser.setUpdatedDate(LocalDate.now());
            this.userRepository.save(testUser);

            testUser = new User();
            testUser.setEmail("emacron@gmail.com");
            testUser.setPseudo("emacron");
            testUser.setPassword(encoder.encode("TestUser3!"));
            testUser.setFirstName("Emmanuel");
            testUser.setLastName("Macron");
            testUser.setRoles(Set.of(UserType.STANDARD, UserType.MODERATOR));
            testUser.setCreatedDate(LocalDate.now());
            testUser.setUpdatedDate(LocalDate.now());
            this.userRepository.save(testUser);

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


        }
    }
}
