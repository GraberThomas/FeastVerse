package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.recipes.RecipeTypeCreateDto;
import graber.thomas.feastverse.model.recipes.RecipeType;
import graber.thomas.feastverse.service.recipes.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Recipes", description = "Endpoints for recipes")
@RestController
@RequestMapping("/recipes/types")
public class RecipeTypeController {
    private final RecipeService recipeService;

    public RecipeTypeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeType> getAllRecipeType(
            @RequestParam(required = false) String name
    ){
        return this.recipeService.getAllRecipesTypes(name);
    }

    @GetMapping("/{typeId}")
    public RecipeType getRecipeType(@Valid @PathVariable UUID typeId){
        return this.recipeService.getRecipeType(typeId).orElseThrow(
                () -> new EntityNotFoundException("No recipe type found for id " + typeId + ".")
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping
    public ResponseEntity<Void> createRecipeType(@Valid @RequestBody RecipeTypeCreateDto recipeTypeCreateDto){
        RecipeType newRecipe = recipeService.createRecipeType(recipeTypeCreateDto.name()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create recipe type.")
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRecipe.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{typeId}")
    public RecipeType updateRecipeType(@Valid @RequestBody RecipeTypeCreateDto recipeTypeCreateDto, @PathVariable UUID typeId){
        return recipeService.updateRecipeType(typeId, recipeTypeCreateDto.name()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update recipe type.")
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{typeId}")
    public ResponseEntity<Void> deleteRecipeType(@PathVariable UUID typeId){
        recipeService.deleteRecipeType(typeId);
        return ResponseEntity.noContent().build();
    }
}
