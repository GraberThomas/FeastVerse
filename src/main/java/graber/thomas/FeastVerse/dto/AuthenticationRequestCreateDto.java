package graber.thomas.FeastVerse.dto;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestCreateDto(String firstName, String lastName, String email, String password) {
}
