package dev.zinchenko.schovage.app.user.repository;

import dev.zinchenko.schovage.app.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(@NonNull String name);

    Optional<Role> findByName(String roleUser);
}
