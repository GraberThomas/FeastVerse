package graber.thomas.FeastVerse.dto.user;

import graber.thomas.FeastVerse.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AdminUserViewDto toAdminUserDto(User user);
    PublicUserViewDto toPublicUserDto(User user);
}
