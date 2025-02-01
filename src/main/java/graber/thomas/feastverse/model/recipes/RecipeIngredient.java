package graber.thomas.feastverse.model.recipes;

import graber.thomas.feastverse.model.ingredient.Ingredient;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue
    @ColumnDefault(value = "gen_random_uuid()")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private Double quantity;

    private boolean required;

    @Column(nullable = false)
    private QuantityType quantity_type;

    private QuantityState ingredient_state;

    private String note;

    public RecipeIngredient() {}
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Double quantity, QuantityType quantity_type, QuantityState ingredient_state) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.quantity_type = quantity_type;
        this.ingredient_state = ingredient_state;
        this.required = false;
    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Double quantity, QuantityType quantity_type, QuantityState ingredient_state, boolean required) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.quantity_type = quantity_type;
        this.ingredient_state = ingredient_state;
        this.required = required;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public QuantityType getQuantity_type() {
        return quantity_type;
    }

    public void setQuantity_type(QuantityType quantityType) {
        this.quantity_type = quantityType;
    }

    public QuantityState getIngredient_state() {
        return ingredient_state;
    }

    public void setIngredient_state(QuantityState ingredient_state) {
        this.ingredient_state = ingredient_state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

}
