package graber.thomas.feastverse.repository.user;

import graber.thomas.feastverse.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * UserRepository provides the data access layer for performing CRUD operations,
 * as well as custom queries for the User entity.
 * <p>
 * This interface extends JpaRepository and JpaSpecificationExecutor to enable the
 * use of JPA specifications and built-in operations for managing User objects.
 * <p>
 * Methods:
 * - findUserByEmail(String email): Retrieves a User by their email address.
 * - findUserByPseudo(String pseudo): Retrieves a User by their pseudo.
 */
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    User findUserByEmail(String email);
    User findUserByPseudo(String pseudo);

    User findUserById(UUID id);
}
