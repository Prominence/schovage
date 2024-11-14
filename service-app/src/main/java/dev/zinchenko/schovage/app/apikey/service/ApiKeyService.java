package dev.zinchenko.schovage.app.apikey.service;

import dev.zinchenko.schovage.app.apikey.dto.ApiKeyResponse;
import dev.zinchenko.schovage.app.apikey.dto.CreateApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.dto.EditApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.entity.ApiKey;
import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.bucket.service.BucketDataService;
import dev.zinchenko.schovage.app.common.exception.NotFoundException;
import dev.zinchenko.schovage.app.common.exception.SchovageServiceException;
import dev.zinchenko.schovage.app.common.utils.SecurityUtils;
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
    private final ApiKeyDataService apiKeyDataService;
    private final BucketDataService bucketDataService;

    public ApiKeyService(ApiKeyDataService apiKeyDataService, BucketDataService bucketDataService) {
        this.apiKeyDataService = apiKeyDataService;
        this.bucketDataService = bucketDataService;
    }

    public List<ApiKeyResponse> findUserKeys() {
        final User user = SecurityUtils.currentUser();
        return apiKeyDataService.findByOwner(user).stream().map(ApiKeyResponse::from).toList();
    }

    public ApiKeyResponse createApiKey(CreateApiKeyRequest request) {
        final User owner = SecurityUtils.currentUser();
        final Bucket bucket = bucketDataService.findByOwnerAndName(owner, request.bucket());
        final ApiKey apiKey = new ApiKey(request.name(), owner, bucket, this.generateKey());
        return Optional.of(apiKeyDataService.save(apiKey)).map(ApiKeyResponse::from).orElseThrow();
    }

    public Optional<ApiKey> findEntityByKey(String apiKey) {
        return apiKeyDataService.findByKey(apiKey);
    }

    public ApiKeyResponse findById(String id) {
        return findEntityById(id)
                .map(ApiKeyResponse::from).orElseThrow(NotFoundException::new);
    }

    public ApiKeyResponse updateApiKey(String id, EditApiKeyRequest request) {
        ApiKey apiKey = findEntityById(id).orElseThrow(NotFoundException::new);
        apiKey.setName(request.name());
        return Optional.of(apiKeyDataService.save(apiKey)).map(ApiKeyResponse::from).orElseThrow();
    }

    public void deleteApiKey(String id) {
        findEntityById(id).ifPresent(apiKeyDataService::delete);
    }

    private Optional<ApiKey> findEntityById(String id) {
        final User user = SecurityUtils.currentUser();
        return apiKeyDataService.findByIdAndOwner(id, user);
    }

    private String generateKey() {
        KeyGenerator keyGen;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new SchovageServiceException(e);
        }
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }
}