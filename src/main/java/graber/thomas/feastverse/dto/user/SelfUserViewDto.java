package graber.thomas.feastverse.dto.user;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing detailed information for an administrative user's view.
 * This class is intended to provide comprehensive user details to be consumed by administrative interfaces.
 * Implements the {@link UserView} interface for basic user identification.
 * <p>
 * Key responsibilities of this class include:
 * - Representing user-related data such as first name, last name, and email.
 * - Including administrative-specific details like roles and timestamps for record creation and updates.
 * - Providing getter and setter methods for accessing and modifying user details.
 */
public class SelfUserViewDto implements UserView {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String pseudo;

    private LocalDate createdDate;

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
