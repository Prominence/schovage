package dev.zinchenko.schovage.app.user;

import dev.zinchenko.schovage.app.user.entity.Role;
import dev.zinchenko.schovage.app.user.service.RoleService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class RolesBootstrap implements ApplicationListener<ApplicationReadyEvent>, PriorityOrdered {
    private final RoleService roleService;

    public RolesBootstrap(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        roleService.createIfNotExist(Role.USER);
        roleService.createIfNotExist(Role.ADMIN);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
