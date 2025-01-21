package graber.thomas.feastverse.service;

public interface SecurityService {
    boolean hasRole(String role);
    String getCurrentUserId();
}