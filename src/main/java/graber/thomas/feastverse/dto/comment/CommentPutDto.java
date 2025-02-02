package graber.thomas.feastverse.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Schema(name = "CommentPutBody")
public record CommentPutDto(
        @Length(max = 500)
        @NotBlank
        String content,

        @NotNull
        UUID owner_id,

        @NotNull
        UUID parent_id,

        @NotNull
        CommentParentType parent_type,

        @NotNull
        boolean is_deleted
) {}
