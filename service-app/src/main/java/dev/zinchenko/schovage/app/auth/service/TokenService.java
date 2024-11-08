package dev.zinchenko.schovage.app.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.zinchenko.schovage.app.auth.properties.JwtTokenProperties;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Service
public class TokenService {

    private final JwtTokenProperties tokenProperties;

    public TokenService(JwtTokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecretKey());
            Instant now = Instant.now();
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plus(tokenProperties.getAccessExpiresIn(), ChronoUnit.SECONDS))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating access token", exception);
        }
    }

    public String generateRefreshToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecretKey());
            Instant now = Instant.now();
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plus(tokenProperties.getRefreshExpiresIn(), ChronoUnit.SECONDS))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating refresh token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecretKey());
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    public Integer getAccessTokenExpiresIn() {
        return tokenProperties.getAccessExpiresIn();
    }

    public Integer getRefreshTokenExpiresIn() {
        return tokenProperties.getRefreshExpiresIn();
    }
}
