package dev.zinchenko.schovage.app.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security.jwt.token")
public class JwtTokenProperties {
    private String secretKey;
    private Integer accessExpiresIn;
    private Integer refreshExpiresIn;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getAccessExpiresIn() {
        return accessExpiresIn;
    }

    public void setAccessExpiresIn(Integer accessExpiresIn) {
        this.accessExpiresIn = accessExpiresIn;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }
}
