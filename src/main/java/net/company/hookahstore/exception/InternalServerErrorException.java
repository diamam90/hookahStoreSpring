package net.company.hookahstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalServerErrorException extends RuntimeException {
   private static final Logger LOGGER = LoggerFactory.getLogger(InternalServerErrorException.class);
    public InternalServerErrorException(String message) {
        super(message);
        LOGGER.warn(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}
