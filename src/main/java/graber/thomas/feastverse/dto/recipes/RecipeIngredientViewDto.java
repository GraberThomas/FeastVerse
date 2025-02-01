package graber.thomas.feastverse.dto.recipes;

import graber.thomas.feastverse.model.recipes.QuantityState;
import graber.thomas.feastverse.model.recipes.QuantityType;

import java.util.UUID;

/**
 * DTO simplifié pour les RecipeIngredient
 */
public record RecipeIngredientViewDto(
        UUID id,
        String ingredient_name,
        Double quantity,
        QuantityType quantity_type,
        QuantityState ingredient_state,
        String note
) {}
