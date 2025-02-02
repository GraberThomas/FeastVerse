package graber.thomas.feastverse.controller.documentation;

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

public class RecipeSwaggerDoc {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get recipe by id",
            description = "Get recipe by id. No authentication required. ADMINISTRATOR role required to get all recipes, and see all recipe fields.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipeGetByIdSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all recipes",
            description = "Get all recipes. No authentication required. Many filter are available. ADMINISTRATOR role required to see all recipe fields and filter on visibility and status.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
    public @interface RecipeGetAllSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all comments for a recipe",
            description = "Get all comments for a recipe. Authentication required. ADMINISTRATOR role required to see all comments fields.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipeGetAllCommentsSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Post a comment for a recipe",
            description = "Post a comment for a recipe. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipePostCommentSwaggerDoc {}


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all comments for a recipe step",
            description = "Get all comments for a recipe step. Authentication required. ADMINISTRATOR role required to see all comments fields.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe step not found.",
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
    public @interface RecipeStepGetAllCommentsSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Post a comment for a recipe step",
            description = "Post a comment for a recipe step. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe step not found.",
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
    public @interface RecipeStepPostCommentSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Create a new report for a recipe",
            description = "Create a new report for a recipe. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipePostReportSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all reports for a recipe",
            description = "Get all reports for a recipe. Authentication required. ADMINISTRATOR or Moderator role required.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipeGetAllReportsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Create a new report for a recipe step",
            description = "Create a new report for a recipe step. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe step not found.",
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
    public @interface RecipeStepPostReportSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all reports for a recipe step",
            description = "Get all reports for a recipe step. Authentication required. ADMINISTRATOR or Moderator role required.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe step not found.",
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
    public @interface RecipeStepGetAllReportsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Like a recipe",
            description = "Like a recipe. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe not found.",
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
    public @interface RecipePostLikeSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete a like for a recipe",
            description = "Delete a like for a recipe. Authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid filter parameters, sort or body.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_400_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access. Invalid authentication token.",
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
                            description = "Forbidden access. User does not have the required role.",
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
                            description = "Recipe like not found.",
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
    public @interface RecipeDeleteLikeSwaggerDoc{}
}
