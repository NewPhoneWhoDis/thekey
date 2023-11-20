package com.the.key;

import com.the.key.exceptions.DataProcessingException;
import com.the.key.exceptions.ExternalApiException;
import com.the.key.exceptions.GenericWebSocketException;
import com.the.key.exceptions.WebSocketProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse> handleExternalApiException(ExternalApiException ex, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponse("API_UNAVAILABLE",
                        "External API is currently unavailable. Please try again later."));
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<ErrorResponse> handleDataProcessingException(DataProcessingException ex,
                                                                       WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("DATA_PROCESSING_ERROR",
                        "An error occurred while processing data."));
    }

    @ExceptionHandler(WebSocketProcessingException.class)
    public ResponseEntity<ErrorResponse> handleWebSocketProcessingException(WebSocketProcessingException ex,
                                                                            WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("WEBSOCKET_ERROR",
                        "WebSocket processing error."));
    }

    @ExceptionHandler(GenericWebSocketException.class)
    public ResponseEntity<ErrorResponse> handleGenericWebSocketException(GenericWebSocketException ex,
                                                                         WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("GENERIC_WEBSOCKET_ERROR",
                        "Unexpected error in WebSocket service."));
    }

    private void logError(Exception ex) {
        logger.error("Error occurred: {}", ex.getMessage(), ex);
    }


    static class ErrorResponse {
        private final String errorCode;
        private final String message;
        private final LocalDateTime timestamp;

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

         public String getErrorCode() {
             return errorCode;
         }

         public String getMessage() {
             return message;
         }

         public LocalDateTime getTimestamp() {
             return timestamp;
         }
     }

}
