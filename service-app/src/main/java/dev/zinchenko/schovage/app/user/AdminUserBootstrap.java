package dev.zinchenko.schovage.app.user;

import dev.zinchenko.schovage.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AdminUserBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(AdminUserBootstrap.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminUserBootstrap(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userService.anyUserExists()) {
            log.warn("No admin user exists in database, creating one...");
            final String adminUsername = Optional.ofNullable(System.getenv("ADMIN_USERNAME")).orElse("admin");
            String adminPassword = System.getenv("ADMIN_PASSWORD");

            if (adminPassword == null) {
                log.warn("No admin password provided, creating one...");
                adminPassword = UUID.randomUUID().toString();
                log.warn("Creating admin user with generated password: {}", adminPassword);
            }

            userService.createAdminUser(adminUsername, passwordEncoder.encode(adminPassword));
        }
    }
}
