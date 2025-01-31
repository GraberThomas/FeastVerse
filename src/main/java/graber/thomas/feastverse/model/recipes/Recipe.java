package graber.thomas.feastverse.model.recipes;

import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    private String image_file_name;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner = null;

    private boolean isPublic = false;
    private boolean isDeleted = false;

    @NotNull
    private String language;

    @NotNull
    private LocalDate createdDate;

    private LocalDate updatedDate;
    
    public Recipe() {}

    public Recipe(
            String title,
            String description,
            Integer preparation_time,
            Integer cooking_time,
            Integer servings_size,
            List<RecipeIngredient> recipeIngredients,
            String image_file_name,
            User owner,
            boolean isPublic,
            boolean isDeleted,
            String language,
            LocalDate createdDate
    ) {
        this.title = title;
        this.description = description;
        this.preparation_time = preparation_time;
        this.cooking_time = cooking_time;
        this.servings_size = servings_size;
        this.recipeIngredients = recipeIngredients;
        this.image_file_name = image_file_name;
        this.owner = owner;
        this.isPublic = isPublic;
        this.isDeleted = isDeleted;
        this.language = language;
        this.createdDate = createdDate;
    }

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
}
