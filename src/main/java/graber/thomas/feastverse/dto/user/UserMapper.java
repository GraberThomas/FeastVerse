package graber.thomas.feastverse.dto.user;

import graber.thomas.feastverse.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting User entities to corresponding Data Transfer Objects (DTOs).
 * Uses MapStruct for object mapping and is configured to be used as a Spring component.
 * This interface defines methods for mapping User domain objects into different views tailored
 * for administrative or public-facing use cases.
 * <p>
 * Responsibilities:
 * - Transform User entities into AdminUserViewDto objects, containing comprehensive user details
 *   suitable for administrative purposes.
 * - Transform User entities into PublicUserViewDto objects, exposing limited information appropriate
 *   for public interfaces.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    AdminUserViewDto toAdminUserDto(User user);
    PublicUserViewDto toPublicUserDto(User user);
    SelfUserViewDto toSelfUserDto(User user);
}
