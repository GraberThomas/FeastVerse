package graber.thomas.feastverse.dto.user;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing public user data for view consumption.
 * This class is designed to expose limited user information intended for public viewing, such as user ID, pseudonym, and creation date.
 * Implements the {@link UserView} interface to ensure basic user identification fields are provided.
 * <p>
 * Responsibilities of this class:
 * - Encapsulates public user data attributes including ID, pseudo, and creation date.
 * - Provides getter and setter methods for interaction with these attributes.
 * - Facilitates transformation of user objects into a form suitable for public-facing interfaces.
 */
public class PublicUserViewDto implements UserView {
    private UUID id;
    private String pseudo;

    private LocalDate createdDate;

    @Override
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
