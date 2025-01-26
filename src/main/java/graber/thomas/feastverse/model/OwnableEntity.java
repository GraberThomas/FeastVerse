package graber.thomas.feastverse.model;

import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OwnableEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner = null;

    private boolean isPublic = false;
    private boolean isDeleted = false;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

}
