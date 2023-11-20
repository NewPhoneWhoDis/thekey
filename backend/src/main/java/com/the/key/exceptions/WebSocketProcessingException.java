package com.the.key.exceptions;

public class WebSocketProcessingException extends RuntimeException {

    public WebSocketProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
