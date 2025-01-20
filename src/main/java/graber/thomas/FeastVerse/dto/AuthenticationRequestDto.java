package graber.thomas.FeastVerse.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestDto(
        @NotBlank(message = "Email can't be empty.")
        String email,

        @NotBlank(message = "Password can't be empty.")
        String password
) {
}
