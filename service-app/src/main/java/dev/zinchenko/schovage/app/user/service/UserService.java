package dev.zinchenko.schovage.app.user.service;

import dev.zinchenko.schovage.app.user.entity.User;
import dev.zinchenko.schovage.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserDataService userDataService;
    private final RoleService roleService;

    public UserService(UserDataService userDataService, RoleService roleService) {
        this.userDataService = userDataService;
        this.roleService = roleService;
    }

    public boolean anyUserExists() {
        return userDataService.count() > 0;
    }

    public void createAdminUser(String username, String encodedPassword) {
        final User adminUser = new User();
        adminUser.setUsername(username);
        adminUser.setPassword(encodedPassword);
        adminUser.setCreatedBy("system");
        adminUser.setUpdatedBy("system");
        adminUser.setRoles(Set.of(roleService.getUserRole(), roleService.getAdminRole()));

        userDataService.save(adminUser);
    }

    public Optional<User> findByUsername(String username) {
        return userDataService.findByUsername(username);
    }
}
