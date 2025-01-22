package graber.thomas.feastverse.dto.user;

import graber.thomas.feastverse.dto.validation.ValidEmail;
import graber.thomas.feastverse.model.user.UserType;
import jakarta.validation.Valid;

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
