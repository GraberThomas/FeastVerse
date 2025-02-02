package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.recipes.RecipeMapper;
import graber.thomas.feastverse.dto.recipes.RecipeViewDto;
import graber.thomas.feastverse.dto.reports.ReportMapper;
import graber.thomas.feastverse.dto.reports.ReportViewDTO;
import graber.thomas.feastverse.dto.user.*;
import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.service.like.LikeService;
import graber.thomas.feastverse.service.report.ReportService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.UUID;

@Tag(name = "User", description = "Endpoints for users")
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final SecurityService securityService;
    private final ReportService reportService;
    private final UserMapper userMapper;
    private final ReportMapper reportMapper;
    private final LikeService likeService;
    private final RecipeMapper recipeMapper;


    public UserController(UserService userService, SecurityService securityService, ReportService reportService, UserMapper userMapper, ReportMapper reportMapper, LikeService likeService, RecipeMapper recipeMapper) {
        this.userService = userService;
        this.reportService = reportService;
        this.userMapper = userMapper;
        this.securityService = securityService;
        this.reportMapper = reportMapper;
        this.likeService = likeService;
        this.recipeMapper = recipeMapper;
    }

    @UserSwaggerDoc.UserGetOneSwaggerDoc
    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable UUID userId) {
        User user = this.userService.get(userId)
                .orElseThrow(() -> new EntityNotFoundException("No user found for id " + userId + "."));

        boolean isAdmin = securityService.hasRole(UserType.ADMINISTRATOR);
        UUID currentUserId = securityService.getCurrentUserId();
        boolean requestedUserIsSelf = userId.equals(currentUserId);

        if (isAdmin) {
            return userMapper.toAdminUserDto(user);
        } else if (requestedUserIsSelf) {
            return userMapper.toSelfUserDto(user);
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
        boolean isAdmin = securityService.hasRole(UserType.ADMINISTRATOR);
        Page<User> users;

        try {
            users = userService.getAllUsers(role, lastName, firstName, pseudo, email, pageable);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return users.map(user ->
                isAdmin
                        ? userMapper.toAdminUserDto(user)
                        : userMapper.toPublicUserDto(user)
        );
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserView getSelf() {
        UUID currentUserId = securityService.getCurrentUserId();
        return this.userService.get(currentUserId)
                .map(userMapper::toSelfUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/recipes/likes")
    public Page<RecipeViewDto> getAllLikedRecipes(Pageable pageable){
        UUID currentUserId = securityService.getCurrentUserId();
        User user = userService.get(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("No user found for id " + currentUserId + ".")
        );
        Page<RecipeLike> recipeLikes = likeService.getLikesForUser(user, pageable);

        return recipeLikes.map(like -> recipeMapper.toRecipeListViewDto(like.getRecipe()));

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

        final boolean isAdmin = securityService.hasRole(UserType.ADMINISTRATOR);

        if (isAdmin) {
            return userMapper.toAdminUserDto(updatedUser);
        } else {
            return userMapper.toSelfUserDto(updatedUser);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MODERATOR')")
    @GetMapping("/{userId}/reports")
    public Page<ReportViewDTO> getReportsForUser(@PathVariable UUID userId, Pageable pageable) {
        return reportService.getByTarget(pageable, userId).map(reportMapper::toReportView);
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