package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.ingredient.IngredientTypeViewDTO;
import graber.thomas.feastverse.service.ingredient.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ingredient", description = "Endpoints for ingredients")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/types")
    public Page<IngredientTypeViewDTO> getAllTypes(Pageable pageable) {
        return ingredientService.getAllTypes(pageable)
                .map(IngredientTypeViewDTO::fromEntity);
    }

}
