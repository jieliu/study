package com.tianma.spring.mvc.controller.common.exception;

/**
 * Created by fiboliu on 16-4-5.
 */

/**
 * For HTTP 404 errros, REST API
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }

    public ResourceNotFoundException(String message) {

        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {

        super(cause);
    }

}
