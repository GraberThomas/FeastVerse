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

    public boolean isOwnedBy(User owner) {
        return this.owner != null && this.owner.equals(owner);
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
