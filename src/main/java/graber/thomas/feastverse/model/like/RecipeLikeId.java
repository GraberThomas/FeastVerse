package graber.thomas.feastverse.model.like;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class RecipeLikeId implements Serializable {
    private UUID recipeId;
    private UUID userId;

    public RecipeLikeId() {
    }

    public RecipeLikeId(UUID recipeId, UUID userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeLikeId that)) return false;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, userId);
    }
}
