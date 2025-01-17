package graber.thomas.FeastVerse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Updated configuration for Spring Security 6.x
        /*
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .cors(cors -> cors.disable()) // Disable CORS (or configure if needed)
                .exceptionHandling(exceptionHandling ->
                    exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/auth/sign-in", "/api/v1/auth/signup", "/h2-console/**").permitAll() // Use 'requestMatchers' instead of 'antMatchers' "/api/test/all"
                                .requestMatchers("/api/v1/books/**").hasAnyRole("ADMINISTRATOR", "BORROWER", "LIBRARIAN")
                                .requestMatchers(new AndRequestMatcher(new AntPathRequestMatcher("/api/v1/books/**", HttpMethod.DELETE.name()))).hasRole(UserType.ADMINISTRATOR.name()) // ADMIN can delete
                                .requestMatchers(new AndRequestMatcher(new AntPathRequestMatcher("/api/v1/books/**", HttpMethod.POST.name()))).hasAnyRole(UserType.ADMINISTRATOR.name(), UserType.LIBRARIAN.name()) // Admin and librarian can create or update
                                .requestMatchers(new AndRequestMatcher(new AntPathRequestMatcher("/api/v1/books/**", HttpMethod.PUT.name()))).hasAnyRole(UserType.ADMINISTRATOR.name(), UserType.LIBRARIAN.name())
                                .requestMatchers(new AndRequestMatcher(new AntPathRequestMatcher("/api/v1/books/**", HttpMethod.GET.name()))).hasAnyRole(UserType.ADMINISTRATOR.name(), UserType.LIBRARIAN.name(), UserType.BORROWER.name()) // Any role can read
                                .anyRequest().authenticated()
                ); */

        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .cors(cors -> cors.disable()) // Disable CORS (or configure if needed)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // -- Add jwt token filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}