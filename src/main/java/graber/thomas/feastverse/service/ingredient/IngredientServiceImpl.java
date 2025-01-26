package graber.thomas.feastverse.service.ingredient;

import graber.thomas.feastverse.dto.ingredient.IngredientCreateDto;
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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientTypeRepository ingredientTypeRepository;
    private final IngredientRepository ingredientRepository;
    private final FileUploadService fileUploadService;
    private final SecurityService securityService;
    private final UserService userService;

    public IngredientServiceImpl(IngredientTypeRepository ingredientTypeRepository, IngredientRepository ingredientRepository, FileUploadService fileUploadService, SecurityService securityService, UserService userService) {
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.ingredientRepository = ingredientRepository;
        this.fileUploadService = fileUploadService;
        this.securityService = securityService;
        this.userService = userService;
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
    public Page<Ingredient> getAllIngredients(String name, Long ingredientTypeId, String ingredientTypeName, Pageable pageable) {
        Specification<Ingredient> spec = IngredientSpecifications.hasName(name);
        spec = spec.and(IngredientSpecifications.hasIngredientType(ingredientTypeId));
        spec = spec.and(IngredientSpecifications.hasIngredientTypeName(ingredientTypeName));
        return this.ingredientRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<Ingredient> getIngredientById(Long id) {
        return this.ingredientRepository.findById(id);
    }

    @Override
    public Optional<Ingredient> createIngredient(IngredientCreateDto ingredientCreateDto, MultipartFile file) {
        User owner  = userService.getById(securityService.getCurrentUserId()).orElseThrow(
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
                throw new RuntimeException("Failed to upload file", e);
            }
        }

        // Create the ingredient entity
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateDto.name());
        ingredient.setDescription(ingredientCreateDto.description());
        ingredient.setType(ingredientType);
        ingredient.setImage_file_name(fileUrl);
        ingredient.setOwner(owner);

        // Save the ingredient to the database
        return Optional.of(ingredientRepository.save(ingredient));
    }
}
