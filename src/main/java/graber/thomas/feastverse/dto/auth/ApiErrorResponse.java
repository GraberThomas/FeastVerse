package graber.thomas.feastverse.dto.auth;

import java.time.LocalDateTime;

public record ApiErrorResponse(LocalDateTime timestamp, int status, String error, String path){}