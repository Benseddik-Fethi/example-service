package com.insy2s.exampleservice.util;

import com.insy2s.exampleservice.domain.Address;
import com.insy2s.exampleservice.domain.User;
import com.insy2s.exampleservice.dto.request.AddressUpdateRequest;
import com.insy2s.exampleservice.dto.request.IAddressRequest;
import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.AddressResponse;
import com.insy2s.exampleservice.dto.response.UserResponse;

import java.time.LocalDate;
import java.util.*;

public class TestObjectFactory {

    public static User createUser() {
        return User.builder()
                .uuid(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1990, 1, 1))
                .addresses(Collections.singleton(createAddress()))
                .build();
    }
    public static Address createAddress() {
       return Address.builder()
                .uuid(UUID.randomUUID())
                .street("123 Test Street")
                .city("TestCity")
                .state("TestState")
                .zip("12345")
                .country("TestCountry")
                .build();
    }


    public static UserRequest createUserRequest() {
        Set<IAddressRequest> addressRequests =Collections.singleton(createIAddressRequest());
        return new UserRequest(
                "Jane",
                "Doe",
                LocalDate.of(1990, 1, 1),
                addressRequests
        );
    }

    public static IAddressRequest createIAddressRequest() {
        return new IAddressRequest(
                "123 Test Lane",
                "TestCity",
                "TestState",
                "12345",
                "TestCountry"
        );
    }
    public static AddressResponse createAddressResponse() {
        return new AddressResponse(
                UUID.randomUUID(),
                "123 Test Avenue",
                "TestCity",
                "TestState",
                "12345",
                "TestCountry"
        );
    }
    public static UserResponse createUserResponse() {
        Set<AddressResponse> addressResponses = Collections.singleton(createAddressResponse());
        return new UserResponse(
                UUID.randomUUID(),
                "Jane",
                "Doe",
                LocalDate.of(1990, 1, 1),
                addressResponses
        );
    }
    public static UserPersonalUpdateRequest createUserPersonalUpdateRequest() {
        Set<AddressUpdateRequest> addressUpdates = Collections.singleton(createAddressUpdateRequest());
        return new UserPersonalUpdateRequest(
                "UpdatedFirstName",
                "UpdatedLastName",
                LocalDate.of(1990, 1, 2), // Updated birth date
                addressUpdates
        );
    }

    public static AddressUpdateRequest createAddressUpdateRequest() {
        return new AddressUpdateRequest(
                "123 Updated Street",
                "UpdatedCity",
                "UpdatedState",
                "54321",
                "UpdatedCountry"
        );
    }

}
