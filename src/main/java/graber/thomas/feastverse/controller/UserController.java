package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.user.UpdateDto;
import graber.thomas.feastverse.dto.user.UserMapper;
import graber.thomas.feastverse.dto.user.UserView;
import graber.thomas.feastverse.model.User;
import graber.thomas.feastverse.service.UserService;
import graber.thomas.feastverse.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "User", description = "Endpoints for users")
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable UUID userId) {
        User user = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isAdmin = SecurityUtils.hasRole("ROLE_ADMINISTRATOR");

        if (isAdmin) {
            return userMapper.toAdminUserDto(user);
        } else {
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
        boolean isAdmin = SecurityUtils.hasRole("ROLE_ADMINISTRATOR");

        if(role != null || lastName != null || firstName != null || pseudo != null || email != null) {
            if(!isAdmin){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only administrators can use filter on users.");
            }
            try {
                return userService.getAllFiltered(role, lastName, firstName, pseudo, email, pageable).map(userMapper::toAdminUserDto);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        Page<User> userPage = userService.getAll(pageable);

        return userPage.map(user -> {
            if (isAdmin) {
                return userMapper.toAdminUserDto(user);
            } else {
                return userMapper.toPublicUserDto(user);
            }
        });
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or #userId == authentication.principal.id")
    @PatchMapping("/{userId}")
    public UserView updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateDto updateDto) {
        User existingUser = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Optional<User> updatedUser = Optional.empty();

        try {
            updatedUser = userService.patch(existingUser, updateDto);
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }

        final boolean isAdmin = SecurityUtils.hasRole("ROLE_ADMINISTRATOR");

        if(updatedUser.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (isAdmin) {
            return userMapper.toAdminUserDto(updatedUser.get());
        } else {
            return userMapper.toPublicUserDto(updatedUser.get());
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