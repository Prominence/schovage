package dev.zinchenko.schovage.core.model;

public class SchovageException extends RuntimeException {

    public SchovageException() {
        super();
    }

    public SchovageException(String message) {
        super(message);
    }

    public SchovageException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchovageException(Throwable cause) {
        super(cause);
    }

    protected SchovageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
