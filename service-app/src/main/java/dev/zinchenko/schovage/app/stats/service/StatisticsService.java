package dev.zinchenko.schovage.app.stats.service;

import dev.zinchenko.schovage.app.apikey.service.ApiKeyUsageService;
import dev.zinchenko.schovage.app.bucket.service.BucketService;
import dev.zinchenko.schovage.app.common.utils.SecurityUtils;
import dev.zinchenko.schovage.app.document.service.DocumentService;
import dev.zinchenko.schovage.app.stats.dto.GeneralStatisticsResponse;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatisticsService {
    private final BucketService bucketService;
    private final DocumentService documentService;
    private final ApiKeyUsageService apiKeyUsageService;

    public StatisticsService(BucketService bucketService, DocumentService documentService, ApiKeyUsageService apiKeyUsageService) {
        this.bucketService = bucketService;
        this.documentService = documentService;
        this.apiKeyUsageService = apiKeyUsageService;
    }

    public GeneralStatisticsResponse getGeneralStatistics() {
        final User requester = SecurityUtils.currentUser();
        final Long bucketsCount = bucketService.countBucketsForUser(requester);
        final BigDecimal storageUsed = documentService.getUsedStorage(requester);
        final Long apiRequestsCount = apiKeyUsageService.getRequestCount(requester);
        return new GeneralStatisticsResponse(bucketsCount, storageUsed, apiRequestsCount);
    }
}
