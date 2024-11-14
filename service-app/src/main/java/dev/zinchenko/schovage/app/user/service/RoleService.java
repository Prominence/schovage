package dev.zinchenko.schovage.app.user.service;

import dev.zinchenko.schovage.app.user.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleDataService roleDataService;

    public RoleService(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }

    public void createIfNotExist(Role role) {
        if (!roleDataService.existsByName(role.getName())) {
            roleDataService.save(role);
        }
    }

    public Role getUserRole() {
        return roleDataService.findByName("ROLE_USER").orElseThrow();
    }

    public Role getAdminRole() {
        return roleDataService.findByName("ROLE_ADMIN").orElseThrow();
    }
}
