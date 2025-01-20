package graber.thomas.FeastVerse.seed.dev;

import graber.thomas.FeastVerse.model.User;
import graber.thomas.FeastVerse.model.UserType;
import graber.thomas.FeastVerse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Component
@Profile("dev") // Ne sera exécuté qu'en mode développement
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

        }
    }
}
