package graber.thomas.feastverse.model.recipes;

import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

}
