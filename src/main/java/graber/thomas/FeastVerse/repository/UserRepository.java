package graber.thomas.FeastVerse.repository;

import graber.thomas.FeastVerse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByEmail(String email);
    User findUserByFirstName(String firstName);
}
