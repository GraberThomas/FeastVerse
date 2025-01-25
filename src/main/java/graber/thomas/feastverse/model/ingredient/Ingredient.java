package graber.thomas.feastverse.model.ingredient;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false) // La colonne dans la table "ingredients"
    private IngredientType type;

    @Length(max = 5000)
    private String description;

    private String image_file_name;

    public Ingredient() {}

    public Ingredient(String name, IngredientType type, String image_file_name, String description) {
        this.name = name;
        this.type = type;
        this.image_file_name = image_file_name;
        this.description = description;
    }

    @Transient
    public String getImage_url() {
        return "/images/ingredient/" + image_file_name;
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

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_url) {
        this.image_file_name = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
