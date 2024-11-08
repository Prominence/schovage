package dev.zinchenko.schovage.app.apikey.entity;

import dev.zinchenko.schovage.app.user.entity.User;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "api_key_request")
public class ApiKeyRequest {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private ApiKey apiKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "transferred_size")
    private BigInteger transferredSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigInteger getTransferredSize() {
        return transferredSize;
    }

    public void setTransferredSize(BigInteger transferredSize) {
        this.transferredSize = transferredSize;
    }
}
