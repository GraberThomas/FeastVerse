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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isOwnedBy(User owner) {
        return this.owner != null && this.owner.equals(owner);
    }
}
