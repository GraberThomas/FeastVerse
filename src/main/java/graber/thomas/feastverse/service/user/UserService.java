package graber.thomas.feastverse.service.user;

import graber.thomas.feastverse.dto.user.UpdateDto;
import graber.thomas.feastverse.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

/**
 * UserService defines the contract for managing and retrieving User
 * information within the application.
 *<p>
 * It provides methods to create, update, delete, and fetch users. It also
 * supports paginated and filtered retrieval of users.
 */
public interface UserService {
    /**
     * Get user by id
     * @param id the user id
     * @return the user find by id
     */
    Optional<User> get(UUID id);


    Page<User> getAllUsers(String role,
                                  String lastName,
                                  String firstName,
                                  String pseudo,
                                  String email,
                                  Pageable pageable
    ) throws AccessDeniedException, IllegalArgumentException;

    /**
     * Get user by username (username)
     * @param username the user's username
     * @return the user find by username
     */
    Optional<User> getByUsername(String username);

    /**
     * Create a new user
     * @param user the user to create
     * @return the new user created
     */
    Optional<User> create(User user);

    /**
     * Update existing user
     * @param user the user to update
     * @return user updated
     */
    Optional<User> update(User user);

    /**
     * Partially updates a user's information based on the provided UpdateDto.
     * Allows modifying specific fields without overwriting entire user data.
     * Ensures that restricted updates, such as role changes, are enforced based
     * on administrative privileges.
     *
     * @param user the existing user entity to be updated
     * @param updateDto an UpdateDto containing the fields to be updated
     * @return an Optional containing the updated User entity if the operation is
     *         successful, or an empty Optional if the update cannot be completed
     */
    Optional<User> patch(User user, UpdateDto updateDto) throws AccessDeniedException;

    /**
     * Delete existing user
     * @param user the user to delete
     */
    void delete(User user);

    Optional<User> getById(UUID id);
}
