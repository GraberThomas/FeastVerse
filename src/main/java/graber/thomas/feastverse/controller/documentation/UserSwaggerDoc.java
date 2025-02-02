package graber.thomas.feastverse.controller.documentation;

import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.dto.user.AdminUserViewDto;
import graber.thomas.feastverse.dto.user.PublicUserViewDto;
import graber.thomas.feastverse.dto.user.SelfUserViewDto;
import graber.thomas.feastverse.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static graber.thomas.feastverse.controller.documentation.ApiErrorDoc.*;


public class UserSwaggerDoc {

    static final String ADMIN_USER_VIEW_EXAMPLE = """
            {
              "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
              "firstName": "John",
              "lastName": "Doe",
              "email": "jdoe@example.com",
              "pseudo": "johnnyD",
              "roles": [
                "ROLE_STANDARD",
                "ROLE_ADMINISTRATOR"
              ],
              "createdDate": "2025-01-28",
              "updatedDate": "2027-01-28"
            }
            """;

    static final String PUBLIC_USER_VIEW_EXAMPLE = """
            {
                "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                "pseudo": "johnnyD",
                "createdDate": "2025-01-28"
            }
            """;

    static final String SELF_USER_VIEW_EXAMPLE = """
            {
                "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                "firstName": "John",
                "lastName": "Doe",
                "email": "jdoe@example.com",
                "pseudo": "johnnyD",
                "createdDate": "2025-01-28"
            }
            """;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Retrieve a user by their id.",
            description = "This endpoint allows to fetch user details by their unique ID. Administrators receive detailed user information, " +
                    "authenticated users can access their own detailed profile, while public information is returned for others.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User details retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            oneOf = { AdminUserViewDto.class, PublicUserViewDto.class, SelfUserViewDto.class },
                                            description = "The response structure varies based on the user's role. " +
                                                    "Administrators receive additional fields (e.g., permissions)."
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = UserSwaggerDoc.PUBLIC_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is not itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Self view",
                                                    value = UserSwaggerDoc.SELF_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Admin view",
                                                    value = UserSwaggerDoc.ADMIN_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is administrator."
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
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
                            description = "User not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )

            }
    )
    public @interface UserGetOneSwaggerDoc {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all users.",
            description = "Allow to get all users, paginated. Admin and moderators have more properties. Have many filter and they can be merged.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users successfully retrieved.",
                            content = @Content(
                                    schema = @Schema(implementation = Page.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = UserSwaggerDoc.PUBLIC_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is not itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Self view",
                                                    value = UserSwaggerDoc.SELF_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Admin view",
                                                    value = UserSwaggerDoc.ADMIN_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is administrator."
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
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
                            description = "User not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface UserGetAllSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get self detail",
            description = "Allow to directly retries current user information's",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users successfully retrieved.",
                            content = @Content(
                                    schema = @Schema(implementation = Page.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = UserSwaggerDoc.PUBLIC_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is not itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Self view",
                                                    value = UserSwaggerDoc.SELF_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Admin view",
                                                    value = UserSwaggerDoc.ADMIN_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is administrator."
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
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
    public @interface UserMeSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Retrieves self liked recipes.",
            description = "Allow an user o retrieves all liked recipes.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get liked recipes.",
                            content = @Content(
                                    schema = @Schema(implementation = Page.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = UserSwaggerDoc.PUBLIC_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is not itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Self view",
                                                    value = UserSwaggerDoc.SELF_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is not administrator and the requested user is itself."
                                            ),
                                            @ExampleObject(
                                                    name = "Admin view",
                                                    value = UserSwaggerDoc.ADMIN_USER_VIEW_EXAMPLE,
                                                    description = "User view returned if user is administrator."
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
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
    public @interface UserLikedRecipeSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Update an user.",
            description = "Allow to patch an user. Only ADMINISTRATOR or self user can perform this action. In case of self update, only a substract of fields are allowed to be pached.",
            responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Successfully updated user.",
                             content = @Content(
                                     schema = @Schema(oneOf = { AdminUserViewDto.class, PublicUserViewDto.class, SelfUserViewDto.class }),
                                     examples = {
                                             @ExampleObject(
                                                     name = "Self view",
                                                     value = UserSwaggerDoc.SELF_USER_VIEW_EXAMPLE,
                                                     description = "User view returned if user is not administrator and the requested user is itself."
                                             ),
                                             @ExampleObject(
                                                     name = "Admin view",
                                                     value = UserSwaggerDoc.ADMIN_USER_VIEW_EXAMPLE,
                                                     description = "User view returned if user is administrator."
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
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_409_EXAMPLE
                                            )
                                    }
                            )
                    )

            }
    )
    public @interface UserUpdateSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get reports related to an user.",
            description = "Get reports related to an user. Restricted to MODERATOR and ADMINISTRATOR",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully receives list  of reports related to the user"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
                            description = "User not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface UserReportSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete an user.",
            description = "Resricted to ADMINISTRATOR. Delete an user in the application.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted user.",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
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
                            description = "User not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )

            }
    )
    public @interface UserDeleteSwaggerDoc{}

}
