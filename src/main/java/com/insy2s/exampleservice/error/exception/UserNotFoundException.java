package com.insy2s.exampleservice.error.exception;

import com.insy2s.exampleservice.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * This class represents a custom exception that is thrown when a user is not found.
 * It extends the RuntimeException class and implements the ExceptionWithErrorResponse interface.
 * @author Fethi Benseddik
 */
@Getter
@ToString
public class UserNotFoundException extends  RuntimeException implements ExceptionWithErrorResponse {

    @Serial
    private static final long serialVersionUID = -5339226418792853545L;
    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;

    public UserNotFoundException(String message) {
        this.message = message;
        this.code = "user.not_found";
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.status = httpStatus.value();
    }
}
