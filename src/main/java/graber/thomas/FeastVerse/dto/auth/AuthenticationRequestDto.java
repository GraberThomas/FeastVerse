package graber.thomas.FeastVerse.dto.auth;


import jakarta.validation.constraints.NotBlank;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestDto(
        @NotBlank(message = "Email or pseudo can't be empty.")
        String username,

        @NotBlank(message = "Password can't be empty.")
        String password
) {
}
