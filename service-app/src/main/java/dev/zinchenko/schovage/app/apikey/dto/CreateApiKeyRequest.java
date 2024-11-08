package dev.zinchenko.schovage.app.apikey.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateApiKeyRequest(@NotBlank String name, @NotBlank String bucket) {
}
