package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.ingredient.*;
import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.service.ingredient.IngredientService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.OwnershipFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Tag(name = "Ingredient", description = "Endpoints for ingredients")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;
    private final SecurityService securityService;
    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    public IngredientController(IngredientService ingredientService, SecurityService securityService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.securityService = securityService;
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
            @RequestParam(required = false, defaultValue = "PUBLIC") VisibilityFilter visibility,
            @RequestParam(required = false, defaultValue = "ALL") OwnershipFilter ownership,
            @RequestParam(required = false, defaultValue = "NOT_DELETED") DeletedFilter deletedStatus,
            @RequestParam(required = false) UUID ownerId,
            Pageable pageable
    ) {
        if(typeId != null && typeName != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot filter by both typeId and typeName");
        }
        if(securityService.hasRole("ROLE_ADMINISTRATOR")){
            return ingredientService.getAllIngredients(name, typeId, typeName, visibility, ownership, deletedStatus, ownerId, pageable)
                    .map(ingredientMapper::toAdminViewDto);
        }
        return ingredientService.getAllIngredients(name, typeId, typeName, visibility, ownership, deletedStatus, ownerId, pageable)
                .map(ingredientMapper::toPublicViewDto);
    }

    @GetMapping("/{ingredientId}")
    public IngredientViewDto getIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientService.getIngredientById(ingredientId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found for id " + ingredientId + ".")
        );
        if(securityService.hasRole("ROLE_ADMINISTRATOR")){
            return ingredientMapper.toAdminViewDto(ingredient);
        }

        return ingredientMapper.toPublicViewDto(ingredient);
    }

    @PreAuthorize("hasRole('ROLE_STANDARD')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngredientViewDto createIngredient(
            @Valid @RequestPart("ingredient") IngredientCreateDto ingredientDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        Ingredient ingredient = ingredientService.createIngredient(ingredientDto, file).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create ingredient.")
        );
        if(securityService.hasRole("ROLE_ADMINISTRATOR")){
            return ingredientMapper.toAdminViewDto(ingredient);
        }
        return ingredientMapper.toPublicViewDto(ingredient);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{ingredientId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngredientViewDto patchIngredient(
            @PathVariable Long ingredientId,
            @Valid @RequestPart("ingredient") IngredientPatchDto ingredientPatchDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        Ingredient ingredient = ingredientService.getIngredientById(ingredientId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found for id " + ingredientId + ".")
        );
        Ingredient patchedIngredient = ingredientService.patchIngredient(ingredient, ingredientPatchDto, file);

        IngredientViewDto dto = ingredientMapper.toPublicViewDto(patchedIngredient);
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping to DTO returned null.");
        }
        return dto;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(
            @PathVariable Long ingredientId,
            @RequestParam(required = false, defaultValue = "false") Boolean hardDelete
    ) {
        if(!securityService.hasRole("ROLE_ADMINISTRATOR") && hardDelete){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only administrator can perform hard delete.");
        }

        this.ingredientService.deleteIngredient(ingredientId, hardDelete);
        return ResponseEntity.noContent().build();
    }

}
