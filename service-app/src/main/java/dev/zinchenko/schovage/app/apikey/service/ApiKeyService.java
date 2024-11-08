package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.dto.ApiKeyResponse;
import dev.zinchenko.schovage.app.apikey.dto.CreateApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.dto.EditApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.apikey.repository.ApiKeyRepository;
import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.bucket.repository.BucketRepository;
import dev.zinchenko.schovage.app.bucket.service.BucketService;
import dev.zinchenko.schovage.app.common.exception.NotFoundException;
import dev.zinchenko.schovage.app.common.util.SecurityUtils;
import dev.zinchenko.schovage.app.user.entity.User;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;
    private final BucketRepository bucketRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository, BucketRepository bucketRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.bucketRepository = bucketRepository;
    }

    public List<ApiKeyResponse> findUserKeys() {
        final User user = SecurityUtils.currentUser();
        return apiKeyRepository.findByOwner(user).stream().map(ApiKeyResponse::from).toList();
    }

    public ApiKeyResponse createApiKey(CreateApiKeyRequest request) {
        final User owner = SecurityUtils.currentUser();
        final Bucket bucket = bucketRepository.findByOwnerAndName(owner, request.bucket());
        final ApiKey apiKey = new ApiKey(request.name(), owner, bucket, generateKey());
        return Optional.of(apiKeyRepository.save(apiKey)).map(ApiKeyResponse::from).orElseThrow();
    }

    private String generateKey() {
        KeyGenerator keyGen;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    public Optional<ApiKey> findEntityByKey(String apiKey) {
        return apiKeyRepository.findByKey(apiKey);
    }

    public ApiKeyResponse findById(String id) {
        return findEntityById(id)
                .map(ApiKeyResponse::from).orElseThrow(NotFoundException::new);
    }

    public ApiKeyResponse updateApiKey(String id, EditApiKeyRequest request) {
        ApiKey apiKey = findEntityById(id).orElseThrow(NotFoundException::new);
        apiKey.setName(request.name());
        return Optional.of(apiKeyRepository.save(apiKey)).map(ApiKeyResponse::from).orElseThrow();
    }

    public void deleteApiKey(String id) {
        findEntityById(id).ifPresent(apiKeyRepository::delete);
    }

    private Optional<ApiKey> findEntityById(String id) {
        final User user = SecurityUtils.currentUser();
        return apiKeyRepository.findByIdAndOwner(id, user);
    }
}