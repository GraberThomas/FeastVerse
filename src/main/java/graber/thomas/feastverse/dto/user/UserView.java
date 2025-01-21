package graber.thomas.feastverse.dto.user;

import java.util.UUID;

/**
 * Interface representing a common view for user-related Data Transfer Objects (DTOs).
 * Defines the minimal set of user attributes that all implementing classes must provide.
 * Primarily used to ensure basic user identification, such as ID, across various user DTO implementations.
 */
public interface UserView {
    UUID getId();
}
