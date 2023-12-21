package com.insy2s.exampleservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.insy2s.exampleservice.domain.User}
 */
public record UserRequest(@NotBlank String firstName, @NotBlank String lastName,
                          @NotNull(message = "La date de naissance ne peut pas etre vide") @Past LocalDate birthDate,
                          Set<IAddressRequest> addresses) implements Serializable {
}