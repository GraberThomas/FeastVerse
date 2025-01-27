package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.dto.ingredient.IngredientCreateDto;
import graber.thomas.feastverse.dto.ingredient.IngredientPatchDto;
import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.OwnershipFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface IngredientService {
    Page<IngredientType> getAllTypes(String name, Pageable pageable);

    Optional<IngredientType> getIngredientTypeById(Long id);

    Page<Ingredient> getAllIngredients(
            String name,
            Long ingredientTypeId,
            String ingredientTypeName,
            VisibilityFilter visibilityFilter,
            OwnershipFilter ownershipFilter,
            DeletedFilter deletedStatus,
            UUID ownerId,
            Pageable pageable
    );

    Optional<Ingredient> getIngredientById(Long id);

    Optional<Ingredient> createIngredient(IngredientCreateDto ingredientCreateDto, MultipartFile file) throws FileUploadException;

    Ingredient patchIngredient(Ingredient ingredient, IngredientPatchDto ingredientPatchDto, MultipartFile file) throws FileUploadException;

    void deleteIngredient(Long id, Boolean hardDelete);
}
