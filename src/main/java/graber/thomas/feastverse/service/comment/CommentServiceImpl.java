package graber.thomas.feastverse.service.comment;

import graber.thomas.feastverse.dto.comment.CommentCreateDto;
import graber.thomas.feastverse.dto.comment.CommentParentType;
import graber.thomas.feastverse.dto.comment.CommentPutDto;
import graber.thomas.feastverse.exception.ForbiddenActionException;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.repository.comments.CommentRepository;
import graber.thomas.feastverse.repository.comments.CommentSpecification;
import graber.thomas.feastverse.service.recipes.RecipeService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import graber.thomas.feastverse.utils.DeletedFilter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final SecurityService securityService;
    private final UserService userService;
    private final RecipeService recipeService;

    public CommentServiceImpl(CommentRepository commentRepository, SecurityService securityService, UserService userService, RecipeService recipeService) {
        this.commentRepository = commentRepository;
        this.securityService = securityService;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Override
    public Optional<Comment> getCommentById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }

        Optional<Comment> commentOpt = this.commentRepository.findById(id);

        if (commentOpt.isEmpty()) {
            return Optional.empty();
        }

        Comment comment = commentOpt.get();
        if (comment.getIsDeleted() &&
                !securityService.hasRole(UserType.ADMINISTRATOR) &&
                !securityService.hasRole(UserType.MODERATOR)) {
            return Optional.empty();
        }

        return commentOpt;
    }

    @Override
    public Page<Comment> getAllComment(UUID ownerId, UUID parentId, DeletedFilter deletedStatus, Pageable pageable) {
        if(!securityService.hasRole(UserType.ADMINISTRATOR) && !securityService.hasRole(UserType.MODERATOR)) {
            if(deletedStatus != DeletedFilter.NOT_DELETED){
                throw new ForbiddenActionException("Only administrator and moderator can view deleted comments");
            }
        }

        Specification<Comment> spec = CommentSpecification.hasOwner(ownerId)
                .and(CommentSpecification.hasParent(parentId));

        if(deletedStatus == DeletedFilter.DELETED){
            spec = spec.and(CommentSpecification.isDeleted());
        }else if(deletedStatus == DeletedFilter.NOT_DELETED){
            spec = spec.and(CommentSpecification.isNotDeleted());
        }
        return this.commentRepository.findAll(spec, pageable);
    }

    @Override
    public void deleteCommentById(UUID id, Boolean hardDelete) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Comment not found for id " + id + ".")
        );

        if(hardDelete){
            if(securityService.hasRole(UserType.ADMINISTRATOR)){
                this.commentRepository.deleteById(id);
            }else{
                throw new ForbiddenActionException("Only administrators can hard delete comments.");
            }
        }

        if(securityService.hasRole(UserType.MODERATOR) || securityService.hasRole(UserType.ADMINISTRATOR)){
            comment.setDeleted(true);
            this.commentRepository.save(comment);
            return;
        }

        UUID userId = securityService.getCurrentUserId();
        if(comment.getOwner().getId().equals(userId)){
            comment.setDeleted(true);
            this.commentRepository.save(comment);
        }else{
            throw new ForbiddenActionException("You cannot delete this comment.");
        }

    }

    @Override
    public Optional<Comment> updateComment(UUID commentId, CommentPutDto commentPutDto) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found for id " + commentId + "."));

        if (!comment.getOwner().getId().equals(commentPutDto.owner_id())) {
            User newOwner = userService.getById(commentPutDto.owner_id())
                    .orElseThrow(() -> new EntityNotFoundException("New owner not found for id " + commentPutDto.owner_id() + "."));
            comment.setOwner(newOwner);
        }

        UUID currentParentId = comment.getParent() != null ? comment.getParent().getId() : null;
        if (currentParentId == null || !currentParentId.equals(commentPutDto.parent_id())) {

            if (comment.getParent() != null) {
                comment.getParent().getComments().remove(comment);
            }


            if (commentPutDto.parent_type() == CommentParentType.RECIPE) {
                Recipe newRecipe = recipeService.getRecipeById(commentPutDto.parent_id())
                        .orElseThrow(() -> new EntityNotFoundException("Recipe not found for id " + commentPutDto.parent_id() + "."));
                comment.setParent(newRecipe);
                newRecipe.addComment(comment);
            } else if (commentPutDto.parent_type() == CommentParentType.RECIPE_STEP) {
                RecipeStep newRecipeStep = recipeService.getRecipeStepById(commentPutDto.parent_id())
                        .orElseThrow(() -> new EntityNotFoundException("Recipe step not found for id " + commentPutDto.parent_id() + "."));
                comment.setParent(newRecipeStep);
                newRecipeStep.addComment(comment);
            }
        }

        comment.setContent(commentPutDto.content());
        comment.setDeleted(commentPutDto.is_deleted());

        return Optional.of(this.commentRepository.save(comment));
    }

    @Override
    public Optional<Comment> createComment(Commentable commentable, UUID userId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment();
        comment.setDeleted(false);
        comment.setContent(commentCreateDto.content());
        comment.setOwner(userService.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id " + userId + ".")));
        comment.setParent(commentable);
        commentable.addComment(comment);
        return Optional.of(this.commentRepository.save(comment));
    }


}
