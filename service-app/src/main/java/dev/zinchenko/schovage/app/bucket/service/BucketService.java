package dev.zinchenko.schovage.app.bucket.service;

import dev.zinchenko.schovage.app.bucket.dto.BucketResponse;
import dev.zinchenko.schovage.app.bucket.dto.CreateBucketRequest;
import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.bucket.repository.BucketRepository;
import dev.zinchenko.schovage.app.common.util.SecurityUtils;
import dev.zinchenko.schovage.app.document.repository.DocumentMetadataRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketService {
    private final DocumentMetadataRepository documentMetadataRepository;
    private final BucketRepository bucketRepository;

    public BucketService(DocumentMetadataRepository documentMetadataRepository, BucketRepository bucketRepository) {
        this.documentMetadataRepository = documentMetadataRepository;
        this.bucketRepository = bucketRepository;
    }

    public List<BucketResponse> findUserBuckets() {
        return bucketRepository.findByOwner(SecurityUtils.currentUser())
                .stream().map(this::mapToResponse).toList();
    }

    public BucketResponse createBucket(CreateBucketRequest request) {
        final User owner = SecurityUtils.currentUser();
        final Bucket bucket = new Bucket();
        bucket.setOwner(owner);
        bucket.setName(request.name());
        return Optional.of(bucketRepository.save(bucket)).map(this::mapToResponse).orElseThrow();
    }

    private BucketResponse mapToResponse(Bucket bucket) {
        final BucketResponse response = new BucketResponse();
        response.setId(bucket.getId());
        response.setName(bucket.getName());
        response.setCreatedAt(bucket.getCreatedAt().toEpochMilli());
        response.setDocumentsCount(documentMetadataRepository.countByBucket(bucket));

        return response;
    }

    public Long countBucketsForUser(User owner) {
        return bucketRepository.countByOwner(owner);
    }
}
