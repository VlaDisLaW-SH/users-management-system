package ru.users_management.mapper;

import org.mapstruct.*;
import ru.users_management.dto.UserCreateDto;
import ru.users_management.dto.UserDto;
import ru.users_management.model.User;
import ru.users_management.service.RoleService;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {RoleService.class}
)
public abstract class UserMapper {

    @Mapping(target = "role", source = "roleName", qualifiedByName = "createRole")
    public abstract User map(UserCreateDto dto);

    @Mapping(target = "roleName", source = "role.roleName")
    public abstract UserDto map(User model);
}
