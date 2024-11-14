package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.apikey.repository.ApiKeyRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyDataService {
    private final ApiKeyRepository repository;

    public ApiKeyDataService(ApiKeyRepository repository) {
        this.repository = repository;
    }

    public List<ApiKey> findByOwner(User user) {
        return repository.findByOwner(user);
    }

    public ApiKey save(ApiKey apiKey) {
        return repository.save(apiKey);
    }

    public Optional<ApiKey> findByKey(String apiKey) {
        return repository.findByKey(apiKey);
    }

    public void delete(ApiKey apiKey) {
        repository.delete(apiKey);
    }

    public Optional<ApiKey> findByIdAndOwner(String id, User user) {
        return repository.findByIdAndOwner(id, user);
    }
}
