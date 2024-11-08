package dev.zinchenko.schovage.app.auth.dto;

public record SignInResponse(String accessToken, String refreshToken, Integer accessTokenExpiresIn, Integer refreshTokenExpiresIn) {
}
