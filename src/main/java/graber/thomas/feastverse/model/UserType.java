package graber.thomas.feastverse.model;

/**
 * Represents the various types of users in the application.
 * <p>
 * This enumeration is used to define the role or permission level of a user.
 * Each user can have one or more roles, such as administrator, moderator,
 * standard user, or banned user. These roles are primarily utilized
 * to manage access control and permissions within the system.
 */
public enum UserType {
    ADMINISTRATOR,
    MODERATOR,
    STANDARD,
    BANNED
}
