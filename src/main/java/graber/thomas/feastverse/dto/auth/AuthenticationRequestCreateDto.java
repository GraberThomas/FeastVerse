package graber.thomas.feastverse.dto.auth;

import graber.thomas.feastverse.dto.auth.validation.ValidEmail;
import graber.thomas.feastverse.dto.auth.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for creating a new authentication request.
 * <p>
 * This record is used to encapsulate the input data required for registering or creating
 * an authentication request. It ensures that each field is properly validated based on
 * defined constraints such as non-blank values and custom annotations for email and password validation.
 * <p>
 * Fields:
 * - `firstName`: The first name of the user. Cannot be empty.
 * - `lastName`: The last name of the user. Cannot be empty.
 * - `email`: The email address of the user. Cannot be empty and must follow a valid email format as per the {@code @ValidEmail} annotation.
 * - `pseudo`: The pseudonym or username of the user. Cannot be empty.
 * - `password`: The password for the user. Cannot be empty and must adhere to the strength rules defined by the {@code @ValidPassword} annotation.
 */
public record AuthenticationRequestCreateDto(
        @NotBlank(message = "Firstname can't be empty.")
        String firstName,

        @NotBlank(message = "Lastname can't be empty.")
        String lastName,

        @NotBlank(message = "Email can't be empty.")
        @ValidEmail
        String email,

        @NotBlank(message = "Pseudo can't be empty.")
        String pseudo,

        @NotBlank(message = "Password can't be empty.")
        @ValidPassword
        String password
) {
}
