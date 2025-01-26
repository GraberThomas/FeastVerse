package graber.thomas.feastverse.model.ingredient;

import graber.thomas.feastverse.model.OwnableEntity;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "ingredients")
public class Ingredient extends OwnableEntity {
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

    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Ingredient() {}

    public Ingredient(String name, IngredientType type, String image_file_name, String description) {
        this.name = name;
        this.type = type;
        this.image_file_name = image_file_name;
        this.description = description;
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
