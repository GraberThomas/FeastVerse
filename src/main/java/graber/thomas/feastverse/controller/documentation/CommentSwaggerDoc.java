package graber.thomas.feastverse.controller.documentation;

import graber.thomas.feastverse.dto.comment.CommentViewDto;
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

import static graber.thomas.feastverse.controller.documentation.ApiErrorDoc.*;

public class CommentSwaggerDoc {
    static final String USER_VIEW_EXAMPLE = """
            {
                "id": "e6f3b10e-b134-4951-ab3b-85fd09336170",
                "content": "J'ai remplacé le vin blanc par du cidre, c'était une tuerie !",
                "createdAt": "2025-02-02T08:05:13.174332",
                "owner_id": "6e5f118e-c9d8-4d43-93d2-cb1483a85169",
                "parent_id": "28bcf28a-763e-4b69-a8ad-9ccf081f1b2d",
                "parent_type": "RECIPE"
            }
            """;

    static final String ADMIN_VIEW_EXAMPLE= """
            {
                "id": "e6f3b10e-b134-4951-ab3b-85fd09336170",
                "content": "J'ai remplacé le vin blanc par du cidre, c'était une tuerie !",
                "createdAt": "2025-02-02T08:05:13.174332",
                "owner_id": "6e5f118e-c9d8-4d43-93d2-cb1483a85169",
                "parent_id": "28bcf28a-763e-4b69-a8ad-9ccf081f1b2d",
                "parent_type": "RECIPE",
                "is_deleted": false
            }
            """;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all comments.",
            description = "Restricted to MODERATORS and ADMINISTRATORS. Allow to get all comments. Results is paginated and you can filter by many parameters. You can use multiple filters.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comments retrieved successfully."
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
    public @interface CommentGetAllSwaggerDoc {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get detailed information of one comments.",
            description = "Get detailed information of one comment. MODERATOR and ADMINISTRATOR have access to more elements.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comments retrieved successfully.",
                            content = @Content(
                                    schema = @Schema(implementation = CommentViewDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = USER_VIEW_EXAMPLE
                                            ),
                                            @ExampleObject(
                                                    name = "Administrator/Moderator view",
                                                    value = ADMIN_VIEW_EXAMPLE
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
                            description = "Comment not found.",
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
    public @interface CommentGetOneSwaggerDoc {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete a comment.",
            description = "Try to delete a comment. Default is soft delete (logical delete). Only ADMINISTRATOR can perform hard delete. A MODERATOR can soft delete any comment. STANDARD user can only soft delete their comments.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content. Comment is deleted"
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
                            description = "Comment not found.",
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
    public @interface CommentDeleteOneSwaggerDoc {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Update a comment.",
            description = "Try to update a comment. Only ADMINISTRATOR can use this operation.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comments updated successfully."
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
                            description = "Comment or user in body not found.",
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
    public @interface CommentPutOneSwaggerDoc {
    }
}
