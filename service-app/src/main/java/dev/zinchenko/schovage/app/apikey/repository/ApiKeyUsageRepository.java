package dev.zinchenko.schovage.app.apikey.repository;

import dev.zinchenko.schovage.app.apikey.entity.ApiKeyUsage;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyUsageRepository extends JpaRepository<ApiKeyUsage, Long> {
    Long countByUser(User user);
}
