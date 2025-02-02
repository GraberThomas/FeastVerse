package graber.thomas.feastverse.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "CommentAdminView")
public record CommentAdminViewDto(
        UUID id,
        String content,
        LocalDateTime createdAt,
        UUID owner_id,
        UUID parent_id,
        String parent_type,
        boolean is_deleted,
        int reportCount
) implements CommentViewDto {}
