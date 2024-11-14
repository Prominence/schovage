package dev.zinchenko.schovage.app.document.service;

import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;
import dev.zinchenko.schovage.app.document.repository.DocumentMetadataRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DocumentMetadataDataService {
    private final DocumentMetadataRepository repository;

    public DocumentMetadataDataService(DocumentMetadataRepository repository) {
        this.repository = repository;
    }

    public Long countByBucket(Bucket bucket) {
        return repository.countByBucket(bucket);
    }

    public DocumentMetadata save(DocumentMetadata documentMetadata) {
        return repository.save(documentMetadata);
    }

    public Optional<DocumentMetadata> findById(String id) {
        return repository.findById(id);
    }

    public BigDecimal getUsedStorage(User user) {
        return repository.getUsedStorage(user);
    }
}
