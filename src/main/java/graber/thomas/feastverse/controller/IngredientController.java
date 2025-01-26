package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.ingredient.IngredientCreateDto;
import graber.thomas.feastverse.dto.ingredient.IngredientMapper;
import graber.thomas.feastverse.dto.ingredient.IngredientTypeViewDto;
import graber.thomas.feastverse.dto.ingredient.IngredientViewDto;
import graber.thomas.feastverse.service.ingredient.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Ingredient", description = "Endpoints for ingredients")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
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
    public IngredientTypeViewDto getById(@PathVariable Long typeId) {
        return IngredientTypeViewDto.fromEntity(ingredientService.getIngredientTypeById(typeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient type not found for id " + typeId + ".")
        ));
    }

    @GetMapping
    public Page<IngredientViewDto> getAllIngredients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String typeName,
            @RequestParam(required = false) Long typeId,
            Pageable pageable
    ) {
        if(typeId != null && typeName != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot filter by both typeId and typeName");
        }
        return ingredientService.getAllIngredients(name, typeId, typeName, pageable)
                .map(ingredientMapper::toViewDto);
    }

    @GetMapping("/{ingredientId}")
    public IngredientViewDto getIngredient(@PathVariable Long ingredientId) {
        return ingredientMapper.toViewDto(
                ingredientService.getIngredientById(ingredientId).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found")
                )
        );
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngredientViewDto createIngredient(
            @Valid @RequestPart("ingredient") IngredientCreateDto ingredientDto,
            @RequestPart("file") MultipartFile file
    ) {
        return ingredientMapper.toViewDto(
                ingredientService.createIngredient(ingredientDto, file).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create ingredient.")
                )
        );
    }
}
