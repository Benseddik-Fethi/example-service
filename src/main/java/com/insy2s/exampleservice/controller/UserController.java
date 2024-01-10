package com.insy2s.exampleservice.controller;

import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;
import com.insy2s.exampleservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * The UserController class is a REST controller that handles user-related API requests.
 */
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final IUserService userService;

    /**
     * It retrieves all users from the database and returns them as a list of UserResponse objects.
     * The list is wrapped in a ResponseEntity with an HTTP status of {@code 200}.
     *
     * @return A ResponseEntity containing a list of UserResponse objects and an HTTP status.
     * @author Fethi Benseddik
     */
    @Operation(
            description = "Retrieve all users",
            summary = "Fetches a list of all users in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponse>>findAll(){
        log.debug("REST request to get all users");
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Fetch a user by UUID.
     *
     * @param uuid The UUID of the user to fetch
     * @return The user with the specified UUID {@code 200},
     * or an error if an exception is thrown {@code 404}.
     * @author Fethi Benseddik
     */
    @Operation(
            description = "Find user by UUID",
            summary = "Provides detailed information about a user with the specified UUID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved user"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found"
                    )
            }
    )
    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> findById(@Parameter(description = "UUID of the user to be obtained")@PathVariable UUID uuid){
        log.debug("REST request to get user by uuid {}", uuid);
        return ResponseEntity.ok(userService.findById(uuid));
    }

    /**
     * Save a user.
     * @param userRequest The UserRequest object representing the user to save.
     * @return {@code 200} The saved user or {@code 400} if the user is not valid.
     * @author Fethi Benseddik
     */
    @Operation(
            description = "Save a user",
            summary = "Saves the provided user details to the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully saved user"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User is not valid"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest){
        log.debug("REST request to save user {}", userRequest);
        return ResponseEntity.ok(userService.save(userRequest));
    }

    /**
     * Delete a user by UUID.
     *
     * @param uuid The UUID of the user to delete.
     * @return A ResponseEntity with an HTTP status of {@code 204} (NO_CONTENT),
     * or an error if an exception is thrown.
     * @author Fethi Benseddik
     */
    @Operation(
            summary = "Delete a user by UUID",
            description = "Deletes a user with the specified UUID from the database",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "User successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found"
                    )
            }
    )
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "UUID of the user to be deleted") @PathVariable UUID uuid){
        log.debug("REST request to delete user by uuid {}", uuid);
        userService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update a user.
     *
     * @param uuid    the uuid of the user to update
     * @param request the new user data
     * @return {@code 200} if the user has been updated,
     * {@code 400} if the user is not valid, or the user is not found,
     * {@code 404} if the user is not found.
     * @author Fethi Benseddik
     */
    @Operation(
            summary = "Update a user",
            description = "Updates user data for a user with the given UUID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully updated"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid user data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found"
                    )
            }
    )
    @PatchMapping("/{uuid}")
    public ResponseEntity<UserResponse> updateUser(@Parameter(description = "UUID of the user to be updated") @PathVariable UUID uuid,
                                                   @RequestBody @Valid UserPersonalUpdateRequest request) {
        log.debug("REST request to update user {} {}", uuid, request);
        return ResponseEntity.ok(userService.updateUser(uuid, request));
    }
}
