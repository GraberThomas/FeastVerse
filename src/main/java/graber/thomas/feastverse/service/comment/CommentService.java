package graber.thomas.feastverse.service.comment;

import graber.thomas.feastverse.dto.comment.CommentCreateDto;
import graber.thomas.feastverse.dto.comment.CommentPutDto;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.comment.Commentable;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.utils.DeletedFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    Optional<Comment> getCommentById(UUID id);

    Page<Comment> getAllComment(UUID ownerId, UUID parentId, DeletedFilter deletedStatus, Pageable pageable);

    void deleteCommentById(UUID id, Boolean hardDelete);

    Optional<Comment> updateComment(UUID commentId, CommentPutDto commentPutDto);

    Optional<Comment> createComment(Commentable commentable, UUID userId, CommentCreateDto commentCreateDto);
}
