package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.controller.documentation.CommentSwaggerDoc;
import graber.thomas.feastverse.dto.comment.CommentAdminViewDto;
import graber.thomas.feastverse.dto.comment.CommentPutDto;
import graber.thomas.feastverse.dto.comment.CommentMapper;
import graber.thomas.feastverse.dto.comment.CommentViewDto;
import graber.thomas.feastverse.model.comment.Comment;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.comment.CommentService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.utils.DeletedFilter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Tag(name = "Comments", description = "Endpoints for manage comments.")
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

    @CommentSwaggerDoc.CommentGetOneSwaggerDoc
    @PreAuthorize( "isAuthenticated()")
    @GetMapping("/{commentId}")
    public CommentViewDto getCommentById(
            @Parameter(description = "The id of the comment.")
            @PathVariable UUID commentId
    ){
        Comment comment = this.commentService.getCommentById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment not found for id " + commentId + ".")
        );

        if(securityService.hasRole(UserType.ADMINISTRATOR) || securityService.hasRole(UserType.MODERATOR)){
            return commentMapper.toCommentAdminViewDto(comment);
        }

        return commentMapper.toCommentPublicViewDto(comment);
    }

    @CommentSwaggerDoc.CommentGetAllSwaggerDoc
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MODERATOR')")
    @GetMapping
    public Page<CommentAdminViewDto> getAllComment(
            @Parameter(description = "Filter on the writer of comment.")
            @RequestParam(required = false) UUID ownerId,
            @Parameter(description = "Filter on the id of recipe or recipe step.")
            @RequestParam(required = false) UUID parentId,
            @Parameter(description = "A filter allow to choose the deleted status of the comment.")
            @RequestParam(required = false, defaultValue = "NOT_DELETED")DeletedFilter deletedStatus,
            @ParameterObject
            @PageableDefault(size = 10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return this.commentService.getAllComment(ownerId, parentId, deletedStatus, pageable).map(commentMapper::toCommentAdminViewDto);
    }

    @CommentSwaggerDoc.CommentDeleteOneSwaggerDoc
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "The id of the comment.")
            @PathVariable UUID commentId,
            @Parameter(description = "Set true to perform hard delete.")
            @RequestParam(required = false, defaultValue = "false") Boolean hardDelete){
        this.commentService.deleteCommentById(commentId, hardDelete);
        return ResponseEntity.noContent().build();
    }

    @CommentSwaggerDoc.CommentPutOneSwaggerDoc
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{commentId}")
    public CommentAdminViewDto putComment(
            @Valid @RequestBody CommentPutDto commentPutDto,
            @Parameter(description = "The id of the comment.")
            @PathVariable UUID commentId
    ){
        Comment updatedComment = this.commentService.updateComment(commentId, commentPutDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update the comment " + commentId + ".")
        );
        return this.commentMapper.toCommentAdminViewDto(updatedComment);
    }

}
