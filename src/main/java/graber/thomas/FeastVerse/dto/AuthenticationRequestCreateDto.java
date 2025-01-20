package graber.thomas.FeastVerse.dto;

import graber.thomas.FeastVerse.dto.validation.ValidEmail;
import graber.thomas.FeastVerse.dto.validation.ValidPassword;
import jakarta.validation.constraints.Email;
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

        @NotBlank(message = "Password can't be empty.")
        @ValidPassword
        String password
) {
}
