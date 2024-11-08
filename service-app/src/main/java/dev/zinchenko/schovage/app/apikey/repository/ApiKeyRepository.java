package dev.zinchenko.schovage.app.apikey.repository;

import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
    List<ApiKey> findByOwner(@NonNull User owner);

    Optional<ApiKey> findByIdAndOwner(@NonNull String apiKey, @NonNull User owner);

    Optional<ApiKey> findByKey(@NonNull String key);
}
