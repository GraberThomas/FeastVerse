package graber.thomas.feastverse.controller.documentation;

import graber.thomas.feastverse.dto.reports.ReportViewDTO;
import graber.thomas.feastverse.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.List;

import static graber.thomas.feastverse.controller.documentation.ApiErrorDoc.*;

public class ReportSwaggerDoc {
    private static final String REPORT_EXAMPLE = """
            {
                "id": "fa153539-c875-4e92-9b99-2680c6fd0fb2",
                "message": "\\"Contenu inapproprié, ne devrait pas être publié.\\"",
                "type": "LOW_QUALITY",
                "reporterId": "784935ae-2bea-41cc-a44a-081c7b448eb9",
                "targetId": "baccfaab-7067-42ae-b525-c236426c43e8",
                "resolved": false,
                "targetType": "RECIPE"
            }
            """;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all reports",
            description = "Get all reports with optional filters",
            responses = {
                   @ApiResponse(
                            responseCode = "200",
                            description = "Reports found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ReportViewDTO.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = REPORT_EXAMPLE
                                            )
                                    }
                            )
                   ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),

            }
    )
    public @interface ReportGetAllSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get report by ID",
            description = "Get a report by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Report found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ReportViewDTO.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = REPORT_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Report not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_404_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface ReportGetByIdSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all report types",
            description = "Get all report types. Authentication not required.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Report types found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = List.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                    ["HATE_SPEECH",
                                                     "INAPPROPRIATE_CONTENT",
                                                     "SPAM",
                                                     "MISINFORMATION",
                                                     "COPYRIGHT_VIOLATION"
                                                     ...
                                                    ]
                                                    """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface ReportGetTypesSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Create a report",
            description = "Create a report",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Report created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = URI.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface ReportCreateSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Update a report",
            description = "Update a report by ID, via PATCH request. Only MODERATOR and ADMINISTRATOR roles are allowed.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Report updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ReportViewDTO.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = REPORT_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Report not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_404_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface ReportUpdateSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete a report",
            description = "Delete a report by ID. Only MODERATOR roles are allowed.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Report deleted"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Report not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_404_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface ReportDeleteSwaggerDoc {}

}
