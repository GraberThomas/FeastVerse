package graber.thomas.feastverse.security;

import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Méthode par défaut — on peut encore l’utiliser si nécessaire pour du login
     * (ex: username = email).
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));

        return buildUserDetails(user);
    }

    /**
     * Nouvelle méthode pour charger l’utilisateur par son UUID.
     */
    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        User user = userService.getById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by id: " + id));

        return buildUserDetails(user);
    }

    /**
     * Factorise la construction de l’objet UserDetails.
     */
    private UserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // CustomUserDetails stocke déjà un UUID, l’email en “username”, etc.
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
