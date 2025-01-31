package graber.thomas.feastverse.model.recipes;

import graber.thomas.feastverse.model.comment.Commentable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "recipe_steps")
@DiscriminatorValue( "RECIPE_STEP")
public class RecipeStep extends Commentable {
    private int step_number;

    @NotBlank
    @Length(max = 5000)
    private String step_description;

    public RecipeStep() {}
    public RecipeStep(int step_number, String step_description) {
        this.step_number = step_number;
        this.step_description = step_description;
    }

    public int getStep_number() {
        return step_number;
    }

    public void setStep_number(int step_number) {
        this.step_number = step_number;
    }

    public String getStep_description() {
        return step_description;
    }

    public void setStep_description(String step_description) {
        this.step_description = step_description;
    }
}
