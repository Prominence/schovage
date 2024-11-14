package dev.zinchenko.schovage.app.common.utils;

import dev.zinchenko.schovage.app.user.entity.User;
import dev.zinchenko.schovage.core.model.SchovageException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils() {}

    public static User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SchovageException("User is not logged");
        }

        if (authentication.getPrincipal() instanceof User user) {
            return user;
        }

        throw new SchovageException("User is not logged");
    }
}
