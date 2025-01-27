package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.dto.ingredient.IngredientCreateDto;
import graber.thomas.feastverse.dto.ingredient.IngredientPatchDto;
import graber.thomas.feastverse.exception.FileDeleteException;
import graber.thomas.feastverse.exception.ForbiddenActionException;
import graber.thomas.feastverse.model.ingredient.Ingredient;
import graber.thomas.feastverse.model.ingredient.IngredientType;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.repository.ingredients.IngredientRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientSpecifications;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeRepository;
import graber.thomas.feastverse.repository.ingredients.IngredientTypeSpecifications;
import graber.thomas.feastverse.service.FileUploadService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import graber.thomas.feastverse.utils.DeletedFilter;
import graber.thomas.feastverse.utils.ImageUrlResolver;
import graber.thomas.feastverse.utils.OwnershipFilter;
import graber.thomas.feastverse.utils.VisibilityFilter;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientTypeRepository ingredientTypeRepository;
    private final IngredientRepository ingredientRepository;
    private final FileUploadService fileUploadService;
    private final SecurityService securityService;
    private final UserService userService;
    private final ImageUrlResolver imageUrlResolver;
    private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    public IngredientServiceImpl(IngredientTypeRepository ingredientTypeRepository, IngredientRepository ingredientRepository, FileUploadService fileUploadService, SecurityService securityService, UserService userService, ImageUrlResolver imageUrlResolver) {
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.ingredientRepository = ingredientRepository;
        this.fileUploadService = fileUploadService;
        this.securityService = securityService;
        this.userService = userService;
        this.imageUrlResolver = imageUrlResolver;
    }

    @Override
    public Page<IngredientType> getAllTypes(String name, Pageable pageable) {
        Specification<IngredientType> spec = IngredientTypeSpecifications.hasName(name);
        return ingredientTypeRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<IngredientType> getIngredientTypeById(Long id) {
        return ingredientTypeRepository.findById(id);
    }

    @Override
    public Page<Ingredient> getAllIngredients(
            String name,
            Long ingredientTypeId,
            String ingredientTypeName,
            VisibilityFilter visibilityFilter,
            OwnershipFilter ownershipFilter,
            DeletedFilter deletedStatus,
            UUID ownerId,
            Pageable pageable
    ) {
        if (visibilityFilter == null) {
            visibilityFilter = VisibilityFilter.PUBLIC;
        }
        if (deletedStatus == null) {
            deletedStatus = DeletedFilter.NOT_DELETED;
        }
        if (visibilityFilter != VisibilityFilter.PUBLIC) {
            if (!securityService.hasRole("ROLE_ADMINISTRATOR")) {
                throw new ForbiddenActionException("Only administrator can get hidden ingredients");
            }
        }
        if (deletedStatus != DeletedFilter.NOT_DELETED) {
            if (!securityService.hasRole("ROLE_ADMINISTRATOR")) {
                throw new ForbiddenActionException("Only administrator can get filter by deleted status");
            }
        }

        if (ownerId != null && ownershipFilter != OwnershipFilter.ALL) {
            throw new IllegalArgumentException("You cannot filter by ownership and id of owner");
        }

        Specification<Ingredient> spec = IngredientSpecifications.hasName(name);
        spec = spec.and(IngredientSpecifications.hasOwner(ownerId));
        spec = spec.and(IngredientSpecifications.hasIngredientType(ingredientTypeId));
        spec = spec.and(IngredientSpecifications.hasIngredientTypeName(ingredientTypeName));
        if (visibilityFilter == VisibilityFilter.PUBLIC) {
            spec = spec.and(IngredientSpecifications.isPublic());
        } else if (visibilityFilter == VisibilityFilter.PRIVATE) {
            spec = spec.and(IngredientSpecifications.isPrivate());
        }
        if (ownershipFilter == OwnershipFilter.CREATED) {
            spec = spec.and(IngredientSpecifications.isCreated());
        } else if (ownershipFilter == OwnershipFilter.DEFAULT) {
            spec = spec.and(IngredientSpecifications.isDefault());
        }
        if (deletedStatus == DeletedFilter.NOT_DELETED) {
            spec = spec.and(IngredientSpecifications.isNotDeleted());
        } else if (deletedStatus == DeletedFilter.DELETED) {
            spec = spec.and(IngredientSpecifications.isDeleted());
        }
        return this.ingredientRepository.findAll(spec, pageable);
    }

    //TODO: Allow the fetch of ingredient if he is in a recipe
    @Override
    public Optional<Ingredient> getIngredientById(Long id) {
        UUID userId = securityService.getCurrentUserId();
        Ingredient ingredient = this.ingredientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ingredient not found for ID: " + id)
        );

        if(securityService.hasRole("ROLE_ADMINISTRATOR")) {
            return Optional.of(ingredient);
        }

        if(ingredient.getIsDeleted()){
            throw new EntityNotFoundException("Ingredient not found for ID: " + id);
        }

        if(!ingredient.getIsPublic() && ingredient.getOwner() != null && !ingredient.getOwner().getId().equals(userId)){
            throw new ForbiddenActionException("You are not allowed to access this ingredient.");
        }

        return Optional.of(ingredient);
    }

    @Override
    public Optional<Ingredient> createIngredient(IngredientCreateDto ingredientCreateDto, MultipartFile file) throws FileUploadException {
        logger.info("Inter in createIngredient");
        logger.info(ingredientCreateDto.toString());
        logger.info(file.toString());

        User owner = userService.getById(securityService.getCurrentUserId()).orElseThrow(
                () -> new EntityNotFoundException("No user found for ID: " + securityService.getCurrentUserId())
        );

        IngredientType ingredientType = this.getIngredientTypeById(ingredientCreateDto.typeId()).orElseThrow(
                () -> new EntityNotFoundException("No ingredient type found for ID: " + ingredientCreateDto.typeId())
        );

        // Upload the file and get its URL (if provided)
        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            try {
                fileUrl = fileUploadService.uploadFile(file);

            } catch (IOException e) {
                throw new FileUploadException("Failed to upload file", e);
            }
        }

        // Create the ingredient entity
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateDto.name());
        ingredient.setDescription(ingredientCreateDto.description());
        ingredient.setType(ingredientType);
        ingredient.setImage_file_name(fileUrl);
        ingredient.setOwner(owner);
        ingredient.setPublic(ingredientCreateDto.isPublic());
        ingredient.setDeleted(false);
        ingredient.setCreatedDate(LocalDate.now());

        // Save the ingredient to the database
        return Optional.of(ingredientRepository.save(ingredient));
    }

    @Override
    public Ingredient patchIngredient(Ingredient ingredient, IngredientPatchDto ingredientPatchDto, MultipartFile file) throws FileUploadException {
        UUID userId = securityService.getCurrentUserId();
        if (!securityService.hasRole("ROLE_ADMINISTRATOR") && !ingredient.getOwner().getId().equals(userId)) {
            throw new ForbiddenActionException("You are not allowed to modify this ingredient.");
        }

        if (file != null) {
            if (!file.isEmpty()) {
                boolean fileDeleted = fileUploadService.deleteFile(imageUrlResolver.extractFileNameWithoutExtension(ingredient.getImage_file_name()));
                if (!fileDeleted) {
                    throw new FileDeleteException("Unable to patch the file.");
                }
                String fileUrl;
                try {
                    fileUrl = fileUploadService.uploadFile(file);
                } catch (IOException e) {
                    throw new FileUploadException("Failed to upload file", e);
                }
                ingredient.setImage_file_name(fileUrl);
                logger.info(fileUrl);
            } else {
                boolean fileDeleted = fileUploadService.deleteFile(imageUrlResolver.extractFileNameWithoutExtension(ingredient.getImage_file_name()));
                if (!fileDeleted) {
                    throw new FileDeleteException("Unable to patch the file.");
                }
                ingredient.setImage_file_name(null);
            }
        }

        if (ingredientPatchDto.isOwnerIdProvided()) {
            if (!securityService.hasRole("ROLE_ADMINISTRATOR")) {
                throw new ForbiddenActionException("Only administrator can change owner of ingredient.");
            }
            User newOwner = userService.getById(ingredientPatchDto.getOwnerId()).orElseThrow(
                    () -> new EntityNotFoundException("No user found for ID: " + ingredientPatchDto.getOwnerId())
            );
            ingredient.setOwner(newOwner);
        }
        if (ingredientPatchDto.isNameProvided()) {
            ingredient.setName(ingredientPatchDto.getName());
        }
        if (ingredientPatchDto.isDescriptionProvided()) {
            ingredient.setDescription(ingredientPatchDto.getDescription());
        }
        if (ingredientPatchDto.isPublicProvided()) {
            ingredientPatchDto.setPublic(ingredientPatchDto.getPublic());
        }
        if (ingredientPatchDto.isTypeProvided()) {
            ingredientPatchDto.setType(ingredientPatchDto.getType());
        }
        this.ingredientRepository.save(ingredient);
        return ingredient;
    }

    @Override
    public void deleteIngredient(Long id, Boolean hardDelete) {
        if (hardDelete) {
            if (!securityService.hasRole("ROLE_ADMINISTRATOR")) {
                throw new ForbiddenActionException("Only administrator can delete ingredients.");
            }
            Ingredient toDelete = this.ingredientRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Ingredient not found for ID: " + id)
            );
            //TODO: Error message when ingredients is in recipe and hard delete
            if (toDelete.getOwner() != null && toDelete.getImage_file_name() != null) {
                fileUploadService.deleteFile(toDelete.getImage_file_name());
            }
            this.ingredientRepository.delete(toDelete);
        } else {
            Ingredient toDelete = this.getIngredientById(id).orElseThrow(
                    () -> new EntityNotFoundException("Ingredient not found for ID: " + id)
            );
            if(!securityService.hasRole("ROLE_ADMINISTRATOR")){
                if(!this.isUserOwner(toDelete, securityService.getCurrentUserId())){
                    throw new ForbiddenActionException("You are not allowed to delete this ingredient.");
                }
            }
            toDelete.setDeleted(true);
            this.ingredientRepository.save(toDelete);
        }
    }

    public boolean isUserOwner(Ingredient ingredient, UUID userId) {
        if (ingredient.getOwner() == null) {
            return false;
        }
        return ingredient.getOwner().getId().equals(userId);
    }
}
