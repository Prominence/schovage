package dev.zinchenko.schovage.app.apikey.controller;

import dev.zinchenko.schovage.app.apikey.dto.ApiKeyResponse;
import dev.zinchenko.schovage.app.apikey.dto.CreateApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.dto.EditApiKeyRequest;
import dev.zinchenko.schovage.app.apikey.service.ApiKeyService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/api-key")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @GetMapping
    public List<ApiKeyResponse> listKeys() {
        return apiKeyService.findUserKeys();
    }

    @GetMapping("{id}")
    public ApiKeyResponse getKeyById(@PathVariable("id") String id) {
        return apiKeyService.findById(id);
    }

    @PostMapping
    public ApiKeyResponse createKey(@RequestBody @Valid CreateApiKeyRequest request) {
        return apiKeyService.createApiKey(request);
    }

    @PatchMapping("{id}")
    public ApiKeyResponse updateKey(@RequestBody @Valid EditApiKeyRequest request, @PathVariable("id") String id) {
        return apiKeyService.updateApiKey(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteKey(@PathVariable("id") String id) {
        apiKeyService.deleteApiKey(id);
    }
}
