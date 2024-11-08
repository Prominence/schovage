package dev.zinchenko.schovage.app.bucket.controller;

import dev.zinchenko.schovage.app.bucket.dto.BucketResponse;
import dev.zinchenko.schovage.app.bucket.dto.CreateBucketRequest;
import dev.zinchenko.schovage.app.bucket.service.BucketService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bucket")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public List<BucketResponse> getBuckets() {
        return bucketService.findUserBuckets();
    }

    @PostMapping
    public BucketResponse createBucket(@RequestBody @Valid CreateBucketRequest request) {
        return bucketService.createBucket(request);
    }
}
