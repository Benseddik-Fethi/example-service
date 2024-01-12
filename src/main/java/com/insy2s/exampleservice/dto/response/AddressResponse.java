package com.insy2s.exampleservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.insy2s.exampleservice.domain.Address}
 */
public record AddressResponse(
        @NotNull
        UUID uuid,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String zip,
        @NotBlank
        String country
) implements Serializable {
}
