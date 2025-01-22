package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.user.UpdateDto;
import graber.thomas.feastverse.dto.user.UserMapper;
import graber.thomas.feastverse.dto.user.UserView;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Tag(name = "User", description = "Endpoints for users")
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final SecurityService securityService;
    private final UserMapper userMapper;


    public UserController(UserService userService, SecurityService securityService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.securityService = securityService;
    }

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable UUID userId) {
        User user = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isAdmin = securityService.hasRole("ROLE_ADMINISTRATOR");
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean requestedUserIsSelf = userId.toString().equals(currentUserId);

        if (isAdmin) {
            return userMapper.toAdminUserDto(user);
        } else if(requestedUserIsSelf){
            return userMapper.toSelfUserDto(user);
        }else {
            return userMapper.toPublicUserDto(user);
        }
    }

    @GetMapping
    public Page<UserView> getAllUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String pseudo,
            @RequestParam(required = false) String email,
            Pageable pageable
    ) {
        boolean isAdmin = securityService.hasRole("ROLE_ADMINISTRATOR");
        Page<User> users;

        try {
            users = userService.getAllUsers(role, lastName, firstName, pseudo, email, pageable);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }

        return users.map(user ->
                isAdmin
                        ? userMapper.toAdminUserDto(user)
                        : userMapper.toPublicUserDto(user)
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or #userId == authentication.principal.id")
    @PatchMapping("/{userId}")
    public UserView updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateDto updateDto) {
        User existingUser = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User updatedUser;
        try {
            updatedUser = userService.patch(existingUser, updateDto)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }

        final boolean isAdmin = securityService.hasRole("ROLE_ADMINISTRATOR");

        if (isAdmin) {
            return userMapper.toAdminUserDto(updatedUser);
        } else {
            return userMapper.toSelfUserDto(updatedUser);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        User existingUser = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.userService.delete(existingUser);
        return ResponseEntity.noContent().build();
    }

}