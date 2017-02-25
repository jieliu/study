package com.tianma.spring.mvc.controller.common.exception;

/**
 * Created by fiboliu on 16-4-5.
 * for HTTP 400 errors, REST API
 */
public final class DataFormatException extends RuntimeException {
    public DataFormatException() {

        super();
    }

    public DataFormatException(String message, Throwable cause) {

        super(message, cause);
    }

    public DataFormatException(String message) {

        super(message);
    }

    public DataFormatException(Throwable cause) {

        super(cause);
    }
}