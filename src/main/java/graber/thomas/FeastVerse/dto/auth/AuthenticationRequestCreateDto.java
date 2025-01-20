package graber.thomas.FeastVerse.dto.auth;

import graber.thomas.FeastVerse.dto.auth.validation.ValidEmail;
import graber.thomas.FeastVerse.dto.auth.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;

/**
 * Authentication request DTO record
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
