package dev.zinchenko.schovage.app.apikey.dto;

import jakarta.validation.constraints.NotBlank;

public record EditApiKeyRequest(@NotBlank String name) {
}
