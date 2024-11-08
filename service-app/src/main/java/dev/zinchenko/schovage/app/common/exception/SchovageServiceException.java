package dev.zinchenko.schovage.app.common.exception;

public class SchovageServiceException extends RuntimeException {
    public SchovageServiceException() {
        super();
    }

    public SchovageServiceException(String message) {
        super(message);
    }

    public SchovageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
