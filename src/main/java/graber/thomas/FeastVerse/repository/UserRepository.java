package graber.thomas.FeastVerse.repository;

import graber.thomas.FeastVerse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByFirstName(String firstName);
}
