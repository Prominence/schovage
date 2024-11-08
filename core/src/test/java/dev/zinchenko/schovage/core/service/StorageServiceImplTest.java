package dev.zinchenko.schovage.core.service;

import dev.zinchenko.schovage.core.conf.StorageSettings;
import dev.zinchenko.schovage.core.model.StoreRequestMetadata;
import dev.zinchenko.schovage.core.model.StoredObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceImplTest {
    private static StorageService storageService;
    private static StorageSettings storageSettings;

    @BeforeAll
    static void beforeAll() {
        try {
            storageSettings = new StorageSettings(Files.createTempDirectory("schovage_test_core").toString());
            storageService = new StorageServiceImpl(storageSettings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void store() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("some text".getBytes(Charset.defaultCharset()));
        StoreRequestMetadata storeRequestMetadata = new StoreRequestMetadata("test1", "test.txt");
        StoredObject storedObject = storageService.store(inputStream, storeRequestMetadata);

        assertTrue(Path.of(storageSettings.getBasePath(), storedObject.objectID().value()).toFile().exists());
    }

    @Test
    void retrieveAsStream() throws IOException {
        String fileContent = "some text";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.defaultCharset()));
        StoreRequestMetadata storeRequestMetadata = new StoreRequestMetadata("test1", "test.txt");
        StoredObject storedObject = storageService.store(byteArrayInputStream, storeRequestMetadata);

        assertNotNull(storedObject);

        String actualFileContent;
        try (InputStream inputStream = storageService.retrieveAsStream(storedObject.objectID())) {
            actualFileContent = new String(inputStream.readAllBytes(), Charset.defaultCharset());
        }

        assertEquals(fileContent, actualFileContent);
    }

    @Test
    void delete() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("some text".getBytes(Charset.defaultCharset()));
        StoreRequestMetadata storeRequestMetadata = new StoreRequestMetadata("test1", "test.txt");
        StoredObject storedObject = storageService.store(inputStream, storeRequestMetadata);

        assertTrue(Path.of(storageSettings.getBasePath(), storedObject.objectID().value()).toFile().exists());

        boolean deleted = storageService.delete(storedObject.objectID());
        assertTrue(deleted);
        assertFalse(Path.of(storageSettings.getBasePath(), storedObject.objectID().value()).toFile().exists());
    }
}