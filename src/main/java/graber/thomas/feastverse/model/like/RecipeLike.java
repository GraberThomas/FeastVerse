package graber.thomas.feastverse.model.like;

import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RecipeLike {

    @EmbeddedId
    private RecipeLikeId id = new RecipeLikeId();

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column(nullable = false)
    private LocalDateTime likedAt = LocalDateTime.now();

    public RecipeLike() {}
    public RecipeLike(Recipe recipe, User user) {
        this.recipe = recipe;
        this.user = user;
        this.likedAt = LocalDateTime.now();
    }

    public RecipeLikeId getId() {
        return id;
    }

    public void setId(RecipeLikeId id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}

