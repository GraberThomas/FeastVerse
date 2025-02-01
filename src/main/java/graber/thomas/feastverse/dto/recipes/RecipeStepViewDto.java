package graber.thomas.feastverse.dto.recipes;

import java.util.UUID;

/**
 * DTO simplifié pour les RecipeStep
 */
public record RecipeStepViewDto(
        UUID id,
        Integer step_number,
        String step_description
) {}
