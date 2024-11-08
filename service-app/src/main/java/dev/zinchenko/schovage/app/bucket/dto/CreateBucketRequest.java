package dev.zinchenko.schovage.app.bucket.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBucketRequest(@NotBlank String name) {
}
