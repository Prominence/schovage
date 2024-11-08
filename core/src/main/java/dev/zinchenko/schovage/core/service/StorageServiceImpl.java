package dev.zinchenko.schovage.core.service;

import dev.zinchenko.schovage.core.conf.StorageSettings;
import dev.zinchenko.schovage.core.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StorageServiceImpl implements StorageService {

    private final StorageSettings storageSettings;

    public StorageServiceImpl(StorageSettings storageSettings) {
        this.storageSettings = storageSettings;
    }

    @Override
    public StoredObject store(InputStream inputStream, StoreRequestMetadata metadata) {
        Path basePath = Path.of(storageSettings.getBasePath());
        Path fileDirPath = basePath.resolve(Path.of(metadata.parentDir()));
        Path filePath = fileDirPath.resolve(metadata.filename());
        try {
            Files.createDirectories(fileDirPath);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String relativePath = basePath.relativize(filePath).toString();
            long size = Files.size(filePath);

            return new StoredObject(new ObjectID(relativePath), size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream retrieveAsStream(ObjectID storedObject) {
        if (storedObject == null) {
            throw new IllegalArgumentException("storedObject cannot be null");
        }

        final Path filePath = Path.of(storageSettings.getBasePath(), storedObject.value());

        try {
            return new FileInputStream(filePath.toFile());
        } catch (FileNotFoundException e) {
            throw new ObjectNotExistException(storedObject);
        }
    }

    @Override
    public boolean delete(ObjectID storedObject) {
        return delete(storedObject, DeleteStrategy.REGULAR);
    }

    @Override
    public boolean delete(ObjectID storedObject, DeleteStrategy strategy) {
        if (storedObject == null) {
            throw new IllegalArgumentException("storedObject cannot be null");
        }

        switch (strategy) {
            case REGULAR -> {
                try {
                    return Files.deleteIfExists(Path.of(storageSettings.getBasePath(), storedObject.value()));
                } catch (IOException e) {
                    throw new SchovageException(e);
                }
            }
            case SAFE -> throw new UnsupportedOperationException(); // TODO: implement later
        }

        return false;
    }
}
