package dev.zinchenko.schovage.app.bucket.repository;

import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, String> {
    List<Bucket> findByOwner(@NonNull User owner);

    Bucket findByOwnerAndName(@NonNull User owner, @NonNull String name);

    long countByOwner(@NonNull User owner);
}
