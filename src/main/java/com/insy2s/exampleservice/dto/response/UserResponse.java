package com.insy2s.exampleservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.insy2s.exampleservice.domain.User}
 */
public record UserResponse(UUID uuid, @NotBlank String firstName, @NotBlank String lastName, @Past LocalDate birthDate,
                           Set<AddressResponse> addresses) implements Serializable {
}