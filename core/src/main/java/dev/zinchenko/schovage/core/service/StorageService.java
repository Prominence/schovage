package dev.zinchenko.schovage.core.service;

import dev.zinchenko.schovage.core.model.DeleteStrategy;
import dev.zinchenko.schovage.core.model.ObjectID;
import dev.zinchenko.schovage.core.model.StoredObject;
import dev.zinchenko.schovage.core.model.StoreRequestMetadata;

import java.io.InputStream;

public interface StorageService {
    StoredObject store(InputStream inputStream, StoreRequestMetadata metadata);

    InputStream retrieveAsStream(ObjectID objectID);

    boolean delete(ObjectID objectID);

    boolean delete(ObjectID objectID, DeleteStrategy deleteType);
}
