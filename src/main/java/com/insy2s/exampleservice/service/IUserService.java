package com.insy2s.exampleservice.service;


import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    /**
     * Retrieves all users.
     * @return A list of UserResponse objects representing the users.
     */
    List<UserResponse> findAll();

    /**
     * Retrieves a user by uuid.
     * @param uuid The uuid of the user to retrieve.
     * @return A UserResponse object representing the user.
     */
    UserResponse findById(UUID uuid);

    /**
     * Saves a user.
     * @param userRequest The UserRequest object representing the user to save.
     * @return A UserResponse object representing the saved user.
     */
    UserResponse save(UserRequest userRequest);

    /**
     * Deletes a user by uuid.
     * @param uuid The uuid of the user to delete.
     */
    void deleteByUuid(UUID uuid);

    /**
     * Updates a user.
     * @param uuid The uuid of the user to update.
     * @param request The UserPersonalUpdateRequest object representing the user to update.
     */
    UserResponse updateUser(UUID uuid, UserPersonalUpdateRequest request);
}
