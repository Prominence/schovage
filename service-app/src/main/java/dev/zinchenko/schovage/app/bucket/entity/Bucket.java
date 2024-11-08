package dev.zinchenko.schovage.app.bucket.entity;

import dev.zinchenko.schovage.app.common.entity.Auditable;
import dev.zinchenko.schovage.app.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "bucket", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"owner_id", "name"})
})
public class Bucket extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
