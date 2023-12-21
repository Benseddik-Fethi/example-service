package com.insy2s.exampleservice.error.record;

/**
 * FieldError is the error response we can send to the client when catching an exception.
 *
 * @param entityName
 * @param fieldName
 * @param message
 * @param code
 */
public record FieldError(
        String entityName,
        String fieldName,
        String message,
        String code
) {
}
