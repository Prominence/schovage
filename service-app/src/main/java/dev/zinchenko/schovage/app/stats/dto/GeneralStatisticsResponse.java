package dev.zinchenko.schovage.app.stats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record GeneralStatisticsResponse(@JsonProperty("total_buckets") long totalBuckets,
                                        @JsonProperty("storage_used") BigDecimal storageUsed,
                                        @JsonProperty("api_request_count") Long apiRequestCount) {
}
