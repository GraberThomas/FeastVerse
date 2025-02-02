package graber.thomas.feastverse.model.recipes;

import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
@DiscriminatorValue( "RECIPE")
public class Recipe extends Commentable {
    @NotBlank
    @Column(nullable = false)
    @Length(max = 150)
    private String title;

    @Length(max = 5000)
    private String description;

    private Integer preparation_time;

    private Integer cooking_time;

    private Integer servings_size;

    private RecipeDifficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private RecipeType type;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeStep> recipe_steps = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags;

    private String author_note;

    private String image_file_name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner = null;

    private boolean is_public = false;
    private boolean is_deleted = false;

    @NotNull
    private String language;

    @NotNull
    private LocalDate createdDate;

    private LocalDate updatedDate;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeLike> likes = new HashSet<>();

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public Recipe() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(Integer preparation_time) {
        this.preparation_time = preparation_time;
    }

    public Integer getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(Integer cooking_time) {
        this.cooking_time = cooking_time;
    }

    public Integer getServings_size() {
        return servings_size;
    }

    public void setServings_size(Integer servings_size) {
        this.servings_size = servings_size;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
        this.image_file_name = image_file_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isPublic() {
        return is_public;
    }

    public void setPublic(boolean aPublic) {
        is_public = aPublic;
    }

    public boolean isDeleted() {
        return is_deleted;
    }

    public void setDeleted(boolean deleted) {
        is_deleted = deleted;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(RecipeDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<RecipeStep> getRecipe_steps() {
        return recipe_steps;
    }

    public void setRecipe_steps(List<RecipeStep> recipe_steps) {
        this.recipe_steps = recipe_steps;
    }

    public String getAuthor_note() {
        return author_note;
    }

    public void setAuthor_note(String author_note) {
        this.author_note = author_note;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<RecipeLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<RecipeLike> likes) {
        this.likes = likes;
    }
}
