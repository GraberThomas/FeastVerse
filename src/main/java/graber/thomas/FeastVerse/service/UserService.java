package graber.thomas.FeastVerse.service;

import graber.thomas.FeastVerse.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

/**
 * User service
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
