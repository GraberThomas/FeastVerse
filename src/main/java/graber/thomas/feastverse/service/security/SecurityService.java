package graber.thomas.feastverse.service.security;

import graber.thomas.feastverse.model.user.User;

import java.util.UUID;

public interface SecurityService {
    boolean hasRole(String role);
    UUID getCurrentUserId();
}