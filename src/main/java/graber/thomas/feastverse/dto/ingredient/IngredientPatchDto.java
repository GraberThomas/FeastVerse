package graber.thomas.feastverse.dto.ingredient;

import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class IngredientPatchDto {
    String name;
    Long type;
    @Length(max = 5000)
    String description;
    Boolean isPublic;
    UUID ownerId;

    boolean isNameProvided = false;
    boolean isTypeProvided = false;
    boolean isDescriptionProvided = false;
    boolean isPublicProvided = false;
    boolean isOwnerIdProvided = false;

    public IngredientPatchDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        isNameProvided = true;
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        isTypeProvided = true;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        isDescriptionProvided = true;
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublicProvided = true;
        isPublic = aPublic;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        isOwnerIdProvided = true;
        this.ownerId = ownerId;
    }

    public boolean isNameProvided() {
        return isNameProvided;
    }

    public boolean isTypeProvided() {
        return isTypeProvided;
    }

    public boolean isDescriptionProvided() {
        return isDescriptionProvided;
    }

    public boolean isPublicProvided() {
        return isPublicProvided;
    }

    public boolean isOwnerIdProvided() {
        return isOwnerIdProvided;
    }
}
