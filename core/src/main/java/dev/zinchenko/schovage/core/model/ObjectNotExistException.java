package dev.zinchenko.schovage.core.model;

public class ObjectNotExistException extends SchovageException {
    private final ObjectID objectID;

    public ObjectNotExistException(ObjectID objectID) {
        this.objectID = objectID;
    }

    @Override
    public String getMessage() {
        return "Stored object '%s' does not exist.".formatted(objectID.value());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
