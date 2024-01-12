package com.insy2s.exampleservice.error;

import com.insy2s.exampleservice.error.exception.UserNotFoundException;
import com.insy2s.exampleservice.error.record.ErrorResponse;
import com.insy2s.exampleservice.error.record.ExceptionWithErrorResponse;
import com.insy2s.exampleservice.error.record.FieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

/**
 * ExceptionsHandler is the class that handle all the exception
 * from the application for witch we want to send an error response to the client.
 *
 * @author Fethi Benseddik
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the MethodArgumentNotValidException to automatically send an error response to the client.
     *
     * @param ex      the exception MethodArgumentNotValidException
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the error response entity
     * @author Fethi Benseddik
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final @NonNull HttpHeaders headers,
            final @NonNull HttpStatusCode status,
            final @NonNull WebRequest request
    ) {
        log.error("Method argument not valid: {}", ex.getMessage());
        List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldError(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getCode()))
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handle the UserNotFoundException to automatically send an error response to the client.
     *
     * @param ex the exception UserNotFoundException
     * @return the error response entity
     * @author Fethi Benseddik
     */
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException ex) {
        log.error("User not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles exceptions that involve a custom error response.
     * It logs an error with the name of the exception class and its message
     * and returns an HTTP response with a 400 Bad Request status code and a response body containing the error details.
     *
     * @param ex The exception with a custom error response
     * @return The HTTP response with a 400 Bad Request status code and a response body containing the error details
     * @author Fethi Benseddik
     */
    protected ResponseEntity<ErrorResponse> handleExceptionWithErrorResponse(final ExceptionWithErrorResponse ex) {
        log.error("Exception with error response: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getCode(),
                ex.getHttpStatus().getReasonPhrase(),
                ex.getStatus(),
                Instant.now());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

}
