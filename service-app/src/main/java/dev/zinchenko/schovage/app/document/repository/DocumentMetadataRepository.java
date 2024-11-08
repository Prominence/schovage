package dev.zinchenko.schovage.app.document.repository;

import dev.zinchenko.schovage.app.bucket.entity.Bucket;
import dev.zinchenko.schovage.app.document.entity.DocumentMetadata;
import dev.zinchenko.schovage.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public interface DocumentMetadataRepository extends JpaRepository<DocumentMetadata, String> {
    long countByBucket(@NonNull Bucket bucket);

    @Query("select sum(d.size) from DocumentMetadata d where d.owner = :user")
    BigDecimal getUsedStorage(@NonNull @Param("user") User user);
}
