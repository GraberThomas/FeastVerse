package graber.thomas.feastverse.dto.user;

import graber.thomas.feastverse.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AdminUserViewDto toAdminUserDto(User user);
    PublicUserViewDto toPublicUserDto(User user);
}
