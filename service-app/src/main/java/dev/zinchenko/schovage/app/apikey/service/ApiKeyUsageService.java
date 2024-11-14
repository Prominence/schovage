package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.apikey.entity.ApiKeyUsage;
import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ApiKeyUsageService {
    private final ApiKeyUsageDataService apiKeyUsageDataService;

    public ApiKeyUsageService(ApiKeyUsageDataService apiKeyUsageDataService) {
        this.apiKeyUsageDataService = apiKeyUsageDataService;
    }

    public void logKeyUsage(ApiKey apiKeyEntity, DocumentMetadata documentMetadata, String requesterIp) {
        final ApiKeyUsage apiKeyUsage = new ApiKeyUsage();
        apiKeyUsage.setApiKey(apiKeyEntity);
        apiKeyUsage.setUser(apiKeyEntity.getOwner());
        apiKeyUsage.setTransferredSize(BigInteger.valueOf(documentMetadata.getSize()));
        apiKeyUsage.setIp(requesterIp);

        apiKeyUsageDataService.save(apiKeyUsage);
    }

    public Long getRequestCount(User user) {
        return apiKeyUsageDataService.countByUser(user);
    }
}
