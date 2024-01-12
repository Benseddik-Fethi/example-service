package com.insy2s.exampleservice.error.record;

import org.springframework.http.HttpStatus;


/**
 * This interface represents an exception that includes an error response.
 *
 * @author Fethi Benseddik
 */
public interface ExceptionWithErrorResponse {
    String getMessage();

    String getCode();

    HttpStatus getHttpStatus();

    int getStatus();

}
