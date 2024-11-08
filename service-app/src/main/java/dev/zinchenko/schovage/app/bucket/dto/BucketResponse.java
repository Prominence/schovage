package dev.zinchenko.schovage.app.bucket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BucketResponse {
    private String id;
    private String name;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("documents_count")
    private Long documentsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDocumentsCount() {
        return documentsCount;
    }

    public void setDocumentsCount(Long documentsCount) {
        this.documentsCount = documentsCount;
    }
}
