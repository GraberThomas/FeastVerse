package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.ingredient.IngredientTypeViewDto;
import graber.thomas.feastverse.service.ingredient.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Ingredient", description = "Endpoints for ingredients")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/types")
    public Page<IngredientTypeViewDto> getAllTypes(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return ingredientService.getAllTypes(name, pageable)
                .map(IngredientTypeViewDto::fromEntity);
    }

    @GetMapping("/types/{typeId}")
    public IngredientTypeViewDto getById(@org.springframework.web.bind.annotation.PathVariable Long typeId) {
        return IngredientTypeViewDto.fromEntity(ingredientService.getById(typeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient type not found for id " + typeId + ".")
        ));
    }

}
