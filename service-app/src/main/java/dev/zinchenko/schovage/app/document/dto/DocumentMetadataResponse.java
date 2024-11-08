package dev.zinchenko.schovage.app.document.dto;

import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link DocumentMetadata}
 */
public class DocumentMetadataResponse implements Serializable {
    private final String id;
    private final String storagePath;
    private final Long size;
    private final String bucketName;
    private final String ownerUsername;

    public static DocumentMetadataResponse from(DocumentMetadata metadata) {
        return new DocumentMetadataResponse(metadata.getId(), metadata.getStoragePath(), metadata.getSize(), metadata.getBucket().getName(), metadata.getOwner().getUsername());
    }

    public DocumentMetadataResponse(String id, String storagePath, Long size, String bucketName, String ownerUsername) {
        this.id = id;
        this.storagePath = storagePath;
        this.size = size;
        this.bucketName = bucketName;
        this.ownerUsername = ownerUsername;
    }

    public String getId() {
        return id;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public Long getSize() {
        return size;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentMetadataResponse entity = (DocumentMetadataResponse) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.storagePath, entity.storagePath) &&
                Objects.equals(this.size, entity.size) &&
                Objects.equals(this.bucketName, entity.bucketName) &&
                Objects.equals(this.ownerUsername, entity.ownerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storagePath, size, bucketName, ownerUsername);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "storagePath = " + storagePath + ", " +
                "size = " + size + ", " +
                "bucketName = " + bucketName + ", " +
                "ownerUsername = " + ownerUsername + ")";
    }
}