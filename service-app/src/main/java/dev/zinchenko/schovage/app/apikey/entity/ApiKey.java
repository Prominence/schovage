package dev.zinchenko.schovage.app.apikey.entity;

import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.common.entity.Auditable;
import dev.zinchenko.schovage.app.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "api_key", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"owner_id", "name"})
})
public class ApiKey extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    private String name;

    private String key;

    public ApiKey() {
    }

    public ApiKey(String name, User owner, Bucket bucket, String key) {
        this.name = name;
        this.owner = owner;
        this.bucket = bucket;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}