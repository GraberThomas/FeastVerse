package graber.thomas.feastverse.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "CommentPublicView")
public record CommentPublicViewDto(
        UUID id,
        String content,
        LocalDateTime createdAt,
        UUID owner_id,
        UUID parent_id,
        String parent_type
) implements CommentViewDto{}
