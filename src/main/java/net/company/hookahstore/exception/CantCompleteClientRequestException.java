package net.company.hookahstore.exception;

public class CantCompleteClientRequestException extends RuntimeException {
    public CantCompleteClientRequestException(String message) {
        super(message);
    }

    public CantCompleteClientRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantCompleteClientRequestException(Throwable cause) {
        super(cause);
    }
}
