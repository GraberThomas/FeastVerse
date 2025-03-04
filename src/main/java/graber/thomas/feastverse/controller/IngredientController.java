package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.controller.documentation.IngredientSwaggerDoc;
import graber.thomas.feastverse.dto.ingredient.*;
import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.ingredient.IngredientService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.OwnershipFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Tag(name = "Ingredient", description = "Endpoints for manage ingredients.")
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

    @IngredientSwaggerDoc.IngredientGetAllTypeSwaggerDoc
    @GetMapping("/types")
    public Page<IngredientTypeViewDto> getAllTypes(
            @Parameter(description = "Allow to filter by name.")
            @RequestParam(required = false) String name,
            @ParameterObject
            @PageableDefault(size = 10, sort="name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ingredientService.getAllTypes(name, pageable)
                .map(IngredientTypeViewDto::fromEntity);
    }

    @IngredientSwaggerDoc.IngredientGetTypeByIdSwaggerDoc
    @GetMapping("/types/{typeId}")
    public IngredientTypeViewDto getById(
            @Parameter(description = "The id of ingredient type.")
            @PathVariable Long typeId
    ) {
        return IngredientTypeViewDto.fromEntity(ingredientService.getIngredientTypeById(typeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient type not found for id " + typeId + ".")
        ));
    }

    @IngredientSwaggerDoc.IngredientGetAllIngredientsSwaggerDoc
    @GetMapping
    public Page<IngredientViewDto> getAllIngredients(
            @Parameter(description = "Allow to filter by name.")
            @RequestParam(required = false) String name,
            @Parameter(description = "Allow to filer by typeName. Incompatible with typeId")
            @RequestParam(required = false) String typeName,
            @Parameter(description = "Allow to filter by typeId. Incompatible with typeName")
            @RequestParam(required = false) Long typeId,
            @Parameter(description = "Allow to filter by visibility status. Only for ADMINISTRATOR.")
            @RequestParam(required = false, defaultValue = "PUBLIC") VisibilityFilter visibility,
            @Parameter(description = "Allow to filter by ownership status.")
            @RequestParam(required = false, defaultValue = "ALL") OwnershipFilter ownership,
            @Parameter(description = "Allow to filter by deleted status.Only for ADMINISTRATOR.")
            @RequestParam(required = false, defaultValue = "NOT_DELETED") DeletedFilter deletedStatus,
            @Parameter(description = "Allow to filter by owner.")
            @RequestParam(required = false) UUID ownerId,
            @ParameterObject
            @PageableDefault(size = 10, sort="name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if(typeId != null && typeName != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot filter by both typeId and typeName");
        }
        if(securityService.hasRole(UserType.ADMINISTRATOR)){
            return ingredientService.getAllIngredients(name, typeId, typeName, visibility, ownership, deletedStatus, ownerId, pageable)
                    .map(ingredientMapper::toAdminViewDto);
        }
        return ingredientService.getAllIngredients(name, typeId, typeName, visibility, ownership, deletedStatus, ownerId, pageable)
                .map(ingredientMapper::toPublicViewDto);
    }

    @IngredientSwaggerDoc.IngredientGetOneIngredientsSwaggerDoc
    @GetMapping("/{ingredientId}")
    public IngredientViewDto getIngredient(
            @Parameter(description = "The id of ingredient.")
            @PathVariable Long ingredientId
    ) {
        Ingredient ingredient = ingredientService.getIngredientById(ingredientId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found for id " + ingredientId + ".")
        );
        if(securityService.hasRole(UserType.ADMINISTRATOR)){
            return ingredientMapper.toAdminViewDto(ingredient);
        }

        return ingredientMapper.toPublicViewDto(ingredient);
    }

    @IngredientSwaggerDoc.IngredientCreateIngredientsSwaggerDoc
    @PreAuthorize("hasRole('ROLE_STANDARD')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngredientViewDto createIngredient(
            @Parameter(description = "Json part needed to create an ingredient.", schema = @Schema(implementation = IngredientCreateDto.class))
            @Valid @RequestPart("ingredient") IngredientCreateDto ingredientDto,
            @Parameter(description = "Not required. Multipart file for upload an image of new ingredient.")
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws FileUploadException {
        Ingredient ingredient = ingredientService.createIngredient(ingredientDto, file).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create ingredient.")
        );
        if(securityService.hasRole(UserType.ADMINISTRATOR)){
            return ingredientMapper.toAdminViewDto(ingredient);
        }
        return ingredientMapper.toPublicViewDto(ingredient);
    }

    @IngredientSwaggerDoc.IngredientUpdateIngredientsSwaggerDoc
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{ingredientId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngredientViewDto patchIngredient(
            @Parameter(description = "The id of ingredient.")
            @PathVariable Long ingredientId,
            @Parameter(description = "Json part needed to update an ingredient.")
            @Valid @RequestPart("ingredient") IngredientPatchDto ingredientPatchDto,
            @Parameter(description = "Not required. Multipart file for upload an image of new ingredient.")
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws FileUploadException {
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

    @IngredientSwaggerDoc.IngredientDeleteIngredientsSwaggerDoc
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(
            @PathVariable Long ingredientId,
            @RequestParam(required = false, defaultValue = "false") Boolean hardDelete
    ) {
        if(!securityService.hasRole(UserType.ADMINISTRATOR) && hardDelete){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only administrator can perform hard delete.");
        }

        this.ingredientService.deleteIngredient(ingredientId, hardDelete);
        return ResponseEntity.noContent().build();
    }

}
