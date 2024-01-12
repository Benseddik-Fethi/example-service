package com.insy2s.exampleservice.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.insy2s.exampleservice.domain.Address}
 */
public record AddressUpdateRequest(
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String zip,
        @NotBlank String country
) implements Serializable {
}
