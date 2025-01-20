package graber.thomas.FeastVerse.dto;

import graber.thomas.FeastVerse.model.UserType;

import java.util.List;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestCreateDto(String firstName, String lastName, String email, String password) {
}
