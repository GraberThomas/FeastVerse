package graber.thomas.FeastVerse.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(LocalDateTime timestamp, int status, String error, String path){}