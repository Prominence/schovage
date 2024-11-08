package dev.zinchenko.schovage.app.apikey.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.zinchenko.schovage.app.apikey.entity.ApiKey;

public class ApiKeyResponse {
    private String id;
    private String name;
    private String bucket;
    private String owner;
    private String key;

    @JsonProperty("created_at")
    private Long createdAt;

    public static ApiKeyResponse from(ApiKey apiKey) {
        ApiKeyResponse response = new ApiKeyResponse();
        response.id = apiKey.getId();
        response.name = apiKey.getName();
        response.bucket = apiKey.getBucket().getName();
        response.key = apiKey.getKey();
        response.owner = apiKey.getOwner().getUsername();
        response.createdAt = apiKey.getCreatedAt().toEpochMilli();

        return response;
    }

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
