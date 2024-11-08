package dev.zinchenko.schovage.app.auth.dto;

public record SignInRequest(
        String username,
        String password) {
}