package graber.thomas.FeastVerse.controller;

import graber.thomas.FeastVerse.dto.user.UserMapper;
import graber.thomas.FeastVerse.dto.user.UserView;
import graber.thomas.FeastVerse.model.User;
import graber.thomas.FeastVerse.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

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
    public UserView getUser(@PathVariable UUID userId, Authentication authentication) {
        User user = this.userService.get(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isAdmin = authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMINISTRATOR"));

        if (isAdmin) {
            return userMapper.toAdminUserDto(user);
        } else {
            return userMapper.toPublicUserDto(user);
        }
    }

    @GetMapping
    public Page<UserView> getAllUsers(
            @RequestParam(required = false) String role,
            Pageable pageable,
            Authentication authentication
    ) {
        boolean isAdmin = authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority ->
                                grantedAuthority.getAuthority().equals("ROLE_ADMINISTRATOR"));

        if(role != null){
            if(!isAdmin){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only administrators can access roles.");
            }
            try {
                return userService.getAllByRole(role, pageable).map(userMapper::toAdminUserDto);
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


}
