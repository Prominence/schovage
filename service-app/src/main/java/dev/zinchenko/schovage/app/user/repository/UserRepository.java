package dev.zinchenko.schovage.app.user.repository;

import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NonNull String username);
}
