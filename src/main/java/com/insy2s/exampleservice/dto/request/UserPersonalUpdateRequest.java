package com.insy2s.exampleservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.insy2s.exampleservice.domain.User}
 */
public record UserPersonalUpdateRequest(@NotBlank String firstName, @NotBlank String lastName,
                                        @Past LocalDate birthDate,
                                        Set<AddressUpdateRequest> addresses) implements Serializable {
}