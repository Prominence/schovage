package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.entity.ApiKeyUsage;
import dev.zinchenko.schovage.app.apikey.repository.ApiKeyUsageRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyUsageDataService {
    private final ApiKeyUsageRepository repository;

    public ApiKeyUsageDataService(ApiKeyUsageRepository apiKeyTraceRepository) {
        this.repository = apiKeyTraceRepository;
    }

    public void save(ApiKeyUsage apiKeyRequest) {
        repository.save(apiKeyRequest);
    }

    public Long countByUser(User user) {
        return repository.countByUser(user);
    }
}
