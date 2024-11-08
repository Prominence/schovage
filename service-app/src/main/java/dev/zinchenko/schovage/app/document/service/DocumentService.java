package dev.zinchenko.schovage.app.document.service;

import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.apikey.repository.ApiKeyRepository;
import dev.zinchenko.schovage.app.apikey.service.ApiKeyTracingService;
import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.common.exception.NotFoundException;
import dev.zinchenko.schovage.app.document.dto.DocumentMetadataResponse;
import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;
import dev.zinchenko.schovage.app.document.repository.DocumentMetadataRepository;
import dev.zinchenko.schovage.app.user.entity.User;
import dev.zinchenko.schovage.core.model.ObjectID;
import dev.zinchenko.schovage.core.model.StoreRequestMetadata;
import dev.zinchenko.schovage.core.model.StoredObject;
import dev.zinchenko.schovage.core.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
    private final StorageService storageService;
    private final ApiKeyRepository apiKeyRepository;
    private final DocumentMetadataRepository documentMetadataRepository;
    private final ApiKeyTracingService apiKeyTracingService;

    public DocumentService(StorageService storageService, ApiKeyRepository apiKeyRepository,
                           DocumentMetadataRepository documentMetadataRepository, ApiKeyTracingService apiKeyTracingService) {
        this.storageService = storageService;
        this.apiKeyRepository = apiKeyRepository;
        this.documentMetadataRepository = documentMetadataRepository;
        this.apiKeyTracingService = apiKeyTracingService;
    }

    @Transactional
    public DocumentMetadataResponse createDocument(String apiKey, MultipartFile document) {
        final ApiKey apiKeyEntity = apiKeyRepository.findByKey(apiKey).orElseThrow();
        final Bucket bucket = apiKeyEntity.getBucket();

        final String parentDir = "%s/%s".formatted(apiKeyEntity.getOwner().getUsername(), bucket.getName());
        final StoreRequestMetadata storeRequest = new StoreRequestMetadata(parentDir, document.getOriginalFilename());

        try {
            final StoredObject storedObject = storageService.store(document.getInputStream(), storeRequest);

            DocumentMetadata documentMetadata = new DocumentMetadata();
            documentMetadata.setId(UUID.randomUUID().toString());
            documentMetadata.setStoragePath(storedObject.objectID().value());
            documentMetadata.setSize(storedObject.size());
            documentMetadata.setBucket(bucket);
            documentMetadata.setApiKey(apiKeyEntity);
            documentMetadata.setOwner(apiKeyEntity.getOwner());

            apiKeyTracingService.logKeyUsage(apiKeyEntity, documentMetadata);

            return Optional.of(documentMetadataRepository.save(documentMetadata)).map(DocumentMetadataResponse::from).orElseThrow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream readDocument(String apiKey, String id) {
        final ApiKey apiKeyEntity = apiKeyRepository.findByKey(apiKey).orElseThrow();
        final DocumentMetadata documentMetadata = documentMetadataRepository.findById(id).orElseThrow();
        if (!documentMetadata.getStoragePath().contains(apiKeyEntity.getBucket().getName())) {
            throw new NotFoundException();
        }
        return storageService.retrieveAsStream(new ObjectID(documentMetadata.getStoragePath()));
    }

    public BigDecimal getUsedStorage(User user) {
        return documentMetadataRepository.getUsedStorage(user);
    }
}
