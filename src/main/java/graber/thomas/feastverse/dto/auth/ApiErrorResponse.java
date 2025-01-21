package graber.thomas.feastverse.dto.auth;

import java.time.LocalDateTime;

/**
 * Represents a standardized structure for API error responses.
 * This record is commonly used to provide meaningful and consistent
 * error details to clients in the event of an API error or exception.
 * <p>
 * Fields:
 * - `timestamp`: The date and time when the error occurred.
 * - `status`: The HTTP status code associated with the error.
 * - `error`: A short description or message about the error.
 * - `path`: The URI path where the error occurred.
 */
public record ApiErrorResponse(LocalDateTime timestamp, int status, String error, String path){}