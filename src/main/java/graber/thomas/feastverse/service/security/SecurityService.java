package graber.thomas.feastverse.service.security;

public interface SecurityService {
    boolean hasRole(String role);
    String getCurrentUserId();
}