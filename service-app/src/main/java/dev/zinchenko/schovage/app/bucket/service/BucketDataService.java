package dev.zinchenko.schovage.app.bucket.service;

import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.bucket.repository.BucketRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketDataService {
    private final BucketRepository repository;

    public BucketDataService(BucketRepository repository) {
        this.repository = repository;
    }

    public Bucket findByOwnerAndName(User owner, String bucket) {
        return repository.findByOwnerAndName(owner, bucket);
    }

    public List<Bucket> findByOwner(User user) {
        return repository.findByOwner(user);
    }

    public Bucket save(Bucket bucket) {
        return repository.save(bucket);
    }

    public Long countByOwner(User owner) {
        return repository.countByOwner(owner);
    }
}
