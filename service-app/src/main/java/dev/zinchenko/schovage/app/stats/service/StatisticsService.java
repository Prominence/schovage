package dev.zinchenko.schovage.app.stats.service;

import dev.zinchenko.schovage.app.apikey.service.ApiKeyTracingService;
import dev.zinchenko.schovage.app.bucket.service.BucketService;
import dev.zinchenko.schovage.app.common.util.SecurityUtils;
import dev.zinchenko.schovage.app.document.service.DocumentService;
import dev.zinchenko.schovage.app.stats.dto.GeneralStatisticsResponse;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatisticsService {
    private final BucketService bucketService;
    private final DocumentService documentService;
    private final ApiKeyTracingService apiKeyTracingService;

    public StatisticsService(BucketService bucketService, DocumentService documentService, ApiKeyTracingService apiKeyTracingService) {
        this.bucketService = bucketService;
        this.documentService = documentService;
        this.apiKeyTracingService = apiKeyTracingService;
    }

    public GeneralStatisticsResponse getGeneralStatistics() {
        final User requester = SecurityUtils.currentUser();
        final Long bucketsCount = bucketService.countBucketsForUser(requester);
        final BigDecimal storageUsed = documentService.getUsedStorage(requester);
        final Long apiRequestsCount = apiKeyTracingService.getRequestCount(requester);
        return new GeneralStatisticsResponse(bucketsCount, storageUsed, apiRequestsCount);
    }
}
