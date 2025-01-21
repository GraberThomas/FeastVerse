package graber.thomas.feastverse.dto.user;

import graber.thomas.feastverse.dto.auth.validation.ValidEmail;
import graber.thomas.feastverse.model.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record UpdateDto(
        String firstName,

        String lastName,

        @ValidEmail
        String email,

        String pseudo,

        @Valid
        Set<UserType> roles
) {
}
