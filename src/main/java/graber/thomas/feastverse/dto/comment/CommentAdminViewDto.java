package graber.thomas.feastverse.dto.comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentAdminViewDto(
        UUID id,
        String content,
        LocalDateTime createdAt,
        UUID owner_id,
        UUID parent_id,
        String parent_type,
        boolean is_deleted
) implements CommentViewDto {}
