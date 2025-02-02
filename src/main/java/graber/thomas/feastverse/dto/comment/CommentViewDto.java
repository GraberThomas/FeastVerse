package graber.thomas.feastverse.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CommentView", oneOf = {CommentAdminViewDto.class, CommentPublicViewDto.class})
public interface CommentViewDto {}
