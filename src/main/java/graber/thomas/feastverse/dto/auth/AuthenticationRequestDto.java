package graber.thomas.feastverse.dto.auth;


import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object representing an authentication request.
 * <p>
 * This record is primarily used for handling login requests where
 * users provide their credentials, such as a username (email or pseudo) and password.
 * It includes validation annotations to ensure that both fields are
 * not blank.
 */
public record AuthenticationRequestDto(
        @NotBlank(message = "Email or pseudo can't be empty.")
        String username,

        @NotBlank(message = "Password can't be empty.")
        String password
) {
}
