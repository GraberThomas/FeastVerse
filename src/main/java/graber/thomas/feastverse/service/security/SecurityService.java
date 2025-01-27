package graber.thomas.feastverse.service.security;

import graber.thomas.feastverse.model.user.UserType;

import java.util.UUID;

public interface SecurityService {
    boolean hasRole(UserType role);
    UUID getCurrentUserId();
}