package graber.thomas.feastverse.exception;

/**
 * Exception thrown when attempting to create a user that already exists.
 * <p>
 * This exception represents a conflict in which the requested operation
 * cannot be completed due to an existing user with the same identification.
 * It is typically mapped to an HTTP 409 Conflict status in a web application.
 * <p>
 * The exception message should provide additional context about the error.
 */
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
