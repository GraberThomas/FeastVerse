package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.auth.AuthenticationRequestCreateDto;
import graber.thomas.feastverse.dto.auth.AuthenticationRequestDto;
import graber.thomas.feastverse.exception.UserAlreadyExistsException;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.security.CustomUserDetails;
import graber.thomas.feastverse.security.JwtUtil;
import graber.thomas.feastverse.security.MyUserDetailsService;
import graber.thomas.feastverse.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


@Tag(name = "Auth", description = "Endpoints for authentification")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            MyUserDetailsService userDetailsService,
            UserService userService,
            PasswordEncoder encoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Operation(
            summary = "User Sign-In",
            description = "Authenticates the user using email or pseudo and password. If successful, returns a JWT access token; otherwise, throws an exception indicating incorrect credentials."
    )
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> authenticateUser(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.username(),
                            authenticationRequestDto.password()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.username());
        // Ancienne version : on utilisait userDetails.getUsername(), qui est l’email ou le username.
        // final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Nouvelle version : on cast en CustomUserDetails pour récupérer l’UUID
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        final UUID userId = customUserDetails.getId(); // On récupère ici l’UUID stocké dans CustomUserDetails

        final String jwt = jwtUtil.generateToken(userId);

        return ResponseEntity.ok(Map.of("accessToken", jwt));
    }


    @Operation(
            summary = "User Registration",
            description = "Registers a new user with the provided credentials. If the username is already taken, an exception is thrown."
    )
    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody AuthenticationRequestCreateDto authenticationRequestCreateDto) {
        // -- Check if the username is already taken
        userService.getByUsername(authenticationRequestCreateDto.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Email is already registered: " + authenticationRequestCreateDto.email());
                });

        userService.getByUsername(authenticationRequestCreateDto.pseudo())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Pseudo is already registered: " + authenticationRequestCreateDto.email());
                });

        // -- Create a new user's account
        User newUser = new User();
        newUser.setEmail(authenticationRequestCreateDto.email());
        newUser.setPassword(encoder.encode(authenticationRequestCreateDto.password()));
        newUser.setFirstName(authenticationRequestCreateDto.firstName());
        newUser.setLastName(authenticationRequestCreateDto.lastName());
        newUser.setRoles(new HashSet<>(Set.of(UserType.STANDARD)));
        newUser.setPseudo(authenticationRequestCreateDto.pseudo());

        return userService.create(newUser)
                .orElseThrow(() -> new RuntimeException("Error occurred while adding user"));
    }
}
