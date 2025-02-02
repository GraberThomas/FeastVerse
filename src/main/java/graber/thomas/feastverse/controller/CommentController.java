package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.comment.CommentPutDto;
import graber.thomas.feastverse.dto.comment.CommentMapper;
import graber.thomas.feastverse.dto.comment.CommentViewDto;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.comment.CommentService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Tag(name = "Comments", description = "Endpoints for comments")
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final SecurityService securityService;

    public CommentController(CommentService commentService, CommentMapper commentMapper, SecurityService securityService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.securityService = securityService;
    }

    @PreAuthorize( "isAuthenticated()")
    @GetMapping("/{commentId}")
    public CommentViewDto getCommentById(@PathVariable UUID commentId){
        Comment comment = this.commentService.getCommentById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment not found for id " + commentId + ".")
        );

        if(securityService.hasRole(UserType.ADMINISTRATOR) || securityService.hasRole(UserType.MODERATOR)){
            return commentMapper.toCommentAdminViewDto(comment);
        }

        return commentMapper.toCommentPublicViewDto(comment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MODERATOR')")
    @GetMapping
    public Page<CommentViewDto> getAllComment(
            @RequestParam(required = false) UUID ownerId,
            @RequestParam(required = false) UUID parentId,
            @RequestParam(required = false, defaultValue = "NOT_DELETED")DeletedFilter deletedStatus,
            @ParameterObject
            @PageableDefault(size = 10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        if(securityService.hasRole(UserType.ADMINISTRATOR) || securityService.hasRole(UserType.MODERATOR)){
            return this.commentService.getAllComment(ownerId, parentId, deletedStatus, pageable).map(commentMapper::toCommentAdminViewDto);
        }

        return this.commentService.getAllComment(ownerId, parentId, deletedStatus, pageable).map(commentMapper::toCommentPublicViewDto);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable UUID commentId,
            @RequestParam(required = false, defaultValue = "false") Boolean hardDelete){
        this.commentService.deleteCommentById(commentId, hardDelete);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{commentId}")
    public CommentViewDto putComment(
            @Valid @RequestBody CommentPutDto commentPutDto,
            @PathVariable UUID commentId
    ){
        Comment updatedComment = this.commentService.updateComment(commentId, commentPutDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update the comment " + commentId + ".")
        );
        return this.commentMapper.toCommentAdminViewDto(updatedComment);
    }

}
