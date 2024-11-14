package dev.zinchenko.schovage.app.bucket.service;

import dev.zinchenko.schovage.app.bucket.dto.BucketResponse;
import dev.zinchenko.schovage.app.bucket.dto.CreateBucketRequest;
import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.common.utils.SecurityUtils;
import dev.zinchenko.schovage.app.document.service.DocumentMetadataDataService;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketService {
    private final DocumentMetadataDataService documentMetadataDataService;
    private final BucketDataService bucketDataService;

    public BucketService(DocumentMetadataDataService documentMetadataDataService, BucketDataService bucketDataService) {
        this.documentMetadataDataService = documentMetadataDataService;
        this.bucketDataService = bucketDataService;
    }

    public List<BucketResponse> findUserBuckets() {
        return bucketDataService.findByOwner(SecurityUtils.currentUser())
                .stream().map(this::mapToResponse).toList();
    }

    public BucketResponse createBucket(CreateBucketRequest request) {
        final User owner = SecurityUtils.currentUser();
        final Bucket bucket = new Bucket();
        bucket.setOwner(owner);
        bucket.setName(request.name());
        return Optional.of(bucketDataService.save(bucket)).map(this::mapToResponse).orElseThrow();
    }

    private BucketResponse mapToResponse(Bucket bucket) {
        final BucketResponse response = new BucketResponse();
        response.setId(bucket.getId());
        response.setName(bucket.getName());
        response.setCreatedAt(bucket.getCreatedAt().toEpochMilli());
        response.setDocumentsCount(documentMetadataDataService.countByBucket(bucket));

        return response;
    }

    public Long countBucketsForUser(User owner) {
        return bucketDataService.countByOwner(owner);
    }
}
