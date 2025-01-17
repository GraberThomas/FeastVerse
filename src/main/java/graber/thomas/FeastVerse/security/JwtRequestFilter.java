package graber.thomas.FeastVerse.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired // or injected our bean in constructor without autowired
    private UserDetailsService userDetailsService;

    @Autowired // or injected our bean in constructor without autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // -- Get the Authorization header from the request
        // Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ
        final String authorizationHeader = request.getHeader("Authorization");

        // -- Initialize username and jwt variables
        String username = null;
        String jwt = null;

        // -- Extract the jwt token from the Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // -- Extract the jwt token from the Authorization header after the "Bearer " string
            jwt = authorizationHeader.substring(7);
            // -- Extract the username from the jwt token
            username = jwtUtil.extractUsername(jwt);
        }

        // Get books/1 => doFilterInternal => Get JWT from header => Build Spring security context with user principal and roles
        // @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
        // Books va appeler d'autres services et que ces autres services

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}