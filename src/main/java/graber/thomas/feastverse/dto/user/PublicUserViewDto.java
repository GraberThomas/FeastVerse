package graber.thomas.feastverse.dto.user;

import java.time.LocalDate;
import java.util.UUID;

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
