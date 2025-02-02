package graber.thomas.feastverse.dto.comment;

import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeStep;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "parent_id", source = "parent.id")
    @Mapping(
            target = "parent_type",
            expression = "java(getParentType(comment.getParent()))"
    )
    @Mapping(target = "owner_id", source = "owner.id")
    CommentPublicViewDto toCommentPublicViewDto(Comment comment);

    @Mapping(target = "parent_id", source = "parent.id")
    @Mapping(
            target = "parent_type",
            expression = "java(getParentType(comment.getParent()))"
    )
    @Mapping(target = "is_deleted", source = "isDeleted")
    @Mapping(target = "owner_id", source = "owner.id")
    CommentAdminViewDto toCommentAdminViewDto(Comment comment);

    default String getParentType(Commentable parent) {
        if (parent == null) {
            return null;
        }
        if (parent instanceof Recipe) {
            return "RECIPE";
        } else if (parent instanceof RecipeStep){
            return "RECIPE_STEP";
        }
        return "UNKNOWN";
    }
}
