package ru.users_management.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;
import ru.users_management.model.Role;
import ru.users_management.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Named("createRole")
    public Role create(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
        return role;
    }
}
