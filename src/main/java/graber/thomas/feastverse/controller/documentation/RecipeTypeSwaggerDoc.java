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

public class RecipeTypeSwaggerDoc {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all recipe types",
            description = "Get all recipe types. No authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
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
                    )
            }
    )
    public @interface RecipeTypeGetALLSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get recipe type by id",
            description = "Get recipe type by id. No authentication required.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
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
                            responseCode = "404",
                            description = "Not found",
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
    public @interface RecipeTypeGetByIdSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Create recipe type",
            description = "Create recipe type. Only administrators can create recipe types.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recipe type created successfully."
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
                            description = "Unauthorized access. Only administrators can create recipe types.",
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
    public @interface RecipeTypeCreateSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete recipe type",
            description = "Update recipe type. Only administrators can update recipe types.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Recipe type deleted successfully."
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
                            description = "Unauthorized access. Only administrators can update recipe types.",
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
    public @interface RecipeTypeDeleteSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Update recipe type",
            description = "Update recipe type. Only administrators can update recipe types.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recipe type updated successfully."
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
                            description = "Unauthorized access. Only administrators can update recipe types.",
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
                            description = "Not found",
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
    public @interface RecipeTypeUpdateSwaggerDoc {}
}
