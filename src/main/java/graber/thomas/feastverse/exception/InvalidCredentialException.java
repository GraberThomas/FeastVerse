package graber.thomas.feastverse.exception;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String message, Throwable cause ) {
        super(message);
    }
}
