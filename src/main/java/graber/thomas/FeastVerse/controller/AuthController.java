package graber.thomas.FeastVerse.controller;

import graber.thomas.FeastVerse.dto.AuthenticationRequestCreateDto;
import graber.thomas.FeastVerse.dto.AuthenticationRequestDto;
import graber.thomas.FeastVerse.model.User;
import graber.thomas.FeastVerse.model.UserType;
import graber.thomas.FeastVerse.security.JwtUtil;
import graber.thomas.FeastVerse.security.MyUserDetailsService;
import graber.thomas.FeastVerse.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            description = "Authenticates the user using email and password. If successful, returns a JWT access token; otherwise, throws an exception indicating incorrect credentials."
    )
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.email(),
                            authenticationRequestDto.password()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.email());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("accessToken", jwt));
    }

    @Operation(
            summary = "User Registration",
            description = "Registers a new user with the provided credentials. If the email is already taken, an exception is thrown."
    )
    @PostMapping("/signup")
    public User registerUser(@RequestBody AuthenticationRequestCreateDto authenticationRequestCreateDto) {
        // -- Check if the username is already taken
        userService.getByUsername(authenticationRequestCreateDto.email())
                .ifPresent(user -> {
                    throw new RuntimeException("Error: user already exists, please sign in");
                });

        // -- Create a new user's account
        User newUser = new User();
        newUser.setEmail(authenticationRequestCreateDto.email());
        newUser.setPassword(encoder.encode(authenticationRequestCreateDto.password()));
        newUser.setFirstName(authenticationRequestCreateDto.firstName());
        newUser.setLastName(authenticationRequestCreateDto.lastName());
        newUser.setRoles(new HashSet<>(Set.of(UserType.STANDARD)));

        return userService.create(newUser)
                .orElseThrow(() -> new RuntimeException("Error occurred while adding user"));
    }
}
