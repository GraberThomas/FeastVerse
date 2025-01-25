package graber.thomas.feastverse.model.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients_type")
public class IngredientType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Length(max = 1000)
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ingredient> ingredients = new ArrayList<>();

    private String image_file_name;

    public IngredientType() {}

    public IngredientType(Long id, String name, String description, String imageFileName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_file_name = imageFileName;
    }

    @Transient
    public String getImage_url() {
        return "/images/ingredient_type/" + image_file_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_name) {
        this.image_file_name = image_name;
    }
}
