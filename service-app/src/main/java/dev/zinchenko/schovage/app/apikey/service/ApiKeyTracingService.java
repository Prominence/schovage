package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.apikey.entity.ApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.repository.ApiKeyRequestRepository;
import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ApiKeyTracingService {
    private final ApiKeyRequestRepository apiKeyRequestRepository;

    public ApiKeyTracingService(ApiKeyRequestRepository apiKeyRequestRepository) {
        this.apiKeyRequestRepository = apiKeyRequestRepository;
    }

    public void logKeyUsage(ApiKey apiKeyEntity, DocumentMetadata documentMetadata) {
        final ApiKeyRequest apiKeyRequest = new ApiKeyRequest();
        apiKeyRequest.setApiKey(apiKeyEntity);
        apiKeyRequest.setUser(apiKeyEntity.getOwner());
        apiKeyRequest.setTransferredSize(BigInteger.valueOf(documentMetadata.getSize()));

        apiKeyRequestRepository.save(apiKeyRequest);
    }

    public Long getRequestCount(User user) {
        return apiKeyRequestRepository.countByUser(user);
    }
}
