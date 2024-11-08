package dev.zinchenko.schovage.app.user.service;

import dev.zinchenko.schovage.app.user.entity.Role;
import dev.zinchenko.schovage.app.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createIfNotExist(Role role) {
        if (!roleRepository.existsByName(role.getName())) {
            roleRepository.save(role);
        }
    }

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow();
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN").orElseThrow();
    }
}
