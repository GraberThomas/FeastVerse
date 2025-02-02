package graber.thomas.feastverse.dto.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentCreateDto (
        @NotBlank
        @Length(max = 500)
        String content
) {}
