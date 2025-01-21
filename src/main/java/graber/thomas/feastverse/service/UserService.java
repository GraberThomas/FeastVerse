package graber.thomas.feastverse.service;

import graber.thomas.feastverse.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Get all users, paginated
     * @return all the users, paginated
     */
    Page<User> getAll(Pageable pageable);

    /**
     * Retrieves a paginated list of users filtered by the given parameters.
     *
     * @param role the role of the user to filter by, can be null to ignore this filter
     * @param lastName the last name of the user to filter by, can be null to ignore this filter
     * @param firstName the first name of the user to filter by, can be null to ignore this filter
     * @param pseudo the pseudo of the user to filter by, can be null to ignore this filter
     * @param email the email of the user to filter by, can be null to ignore this filter
     * @param pageable the pagination and sorting information
     * @return a paginated list of users matching the specified filters
     */
    Page<User> getAllFiltered(String role, String lastName, String firstName, String pseudo, String email, Pageable pageable);

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
     * Delete existing user
     * @param user the user to delete
     */
    void delete(User user);
}
