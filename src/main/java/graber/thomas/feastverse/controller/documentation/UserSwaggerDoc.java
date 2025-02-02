package graber.thomas.feastverse.controller.documentation;

import graber.thomas.feastverse.dto.user.AdminUserViewDto;
import graber.thomas.feastverse.dto.user.PublicUserViewDto;
import graber.thomas.feastverse.dto.user.SelfUserViewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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

            }
    )
    public @interface UserGetOneSwaggerDoc {
    }
}
