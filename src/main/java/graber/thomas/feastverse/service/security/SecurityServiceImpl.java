package graber.thomas.feastverse.service.security;

import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean hasRole(UserType role) {
        String roleName = "ROLE_" + role.name();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }

    @Override
    public UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            return null;
        }

        return userDetails.getId();
    }
}