package graber.thomas.feastverse.controller;

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
                                                    name = "Standard response.",
                                                    value = """
                                                            {
                                                                 "id": "5fa77e3f-1a83-437e-8252-8f44b5ab61a0",
                                                                 "pseudo": "aPseudo",
                                                                 "createdDate": "2025-01-27"
                                                            }
                                                            """
                                            ),
                                            @ExampleObject(
                                                    name = "Administrator response.",
                                                    value = """
                                                            {
                                                                    "id": "5fa77e3f-1a83-437e-8252-8f44b5ab61a0",
                                                                    "firstName": "Jean",
                                                                    "lastName": "Dupont",
                                                                    "email": "jdupont@test.com",
                                                                    "pseudo": "aPseudo",
                                                                    "roles": [
                                                                        "STANDARD"
                                                                    ],
                                                                    "createdDate": "2025-01-27",
                                                                    "updatedDate": "2025-01-27"
                                                            }
                                                            """
                                            ),
                                            @ExampleObject(
                                                    name = "Self request response.",
                                                    value = """
                                                            {
                                                                "id": "9f197eb0-fa60-4363-84ac-0065aab8b119",
                                                                "firstName": "Jean",
                                                                "lastName": "Dupont",
                                                                "email": "jdupont@test.com",
                                                                "pseudo": "aPseudo",
                                                                "createdDate": "2025-01-27"
                                                            }
                                                            """
                                            )
                                    }
                            )
                    ),

            }
    )
    public @interface UserGetOneSwaggerDoc {
    }
}
