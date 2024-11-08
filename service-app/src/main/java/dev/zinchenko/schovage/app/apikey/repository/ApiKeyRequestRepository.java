package dev.zinchenko.schovage.app.apikey.repository;

import dev.zinchenko.schovage.app.apikey.entity.ApiKeyRequest;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRequestRepository extends JpaRepository<ApiKeyRequest, Long> {
    Long countByUser(User user);
}
