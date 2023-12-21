package com.insy2s.exampleservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;
import com.insy2s.exampleservice.error.exception.UserNotFoundException;
import com.insy2s.exampleservice.service.IUserService;
import com.insy2s.exampleservice.util.TestObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.insy2s.exampleservice.error.record.ErrorMessage.USER_NOT_FOUND;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserService userService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll returns all users")
    void findAllReturnsAllUsers() throws Exception {
        UserResponse userResponse1 = TestObjectFactory.createUserResponse();
        UserResponse userResponse2 = TestObjectFactory.createUserResponse();
        List<UserResponse> userResponses = Arrays.asList(userResponse1, userResponse2);

        when(userService.findAll()).thenReturn(userResponses);
        mockMvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponses)));
    }

    @Test
    @DisplayName("findById returns user when user exists")
    void findByIdReturnsUserWhenUserExists() throws Exception {
        UUID uuid = UUID.randomUUID();
        UserResponse userResponse = TestObjectFactory.createUserResponse();

        when(userService.findById(uuid)).thenReturn(userResponse);

        mockMvc.perform(get("/api/v1/user/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponse)));
    }

    @Test
    @DisplayName("findById returns not found when user does not exist")
    void findByIdReturnsNotFoundWhenUserDoesNotExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(userService.findById(uuid)).thenThrow(new UserNotFoundException(USER_NOT_FOUND));
        mockMvc.perform(get("/api/v1/user/" + uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("save returns saved user")
    void saveReturnsSavedUser() throws Exception {
        UserRequest userRequest = TestObjectFactory.createUserRequest();
        UserResponse userResponse = TestObjectFactory.createUserResponse();

        when(userService.save(userRequest)).thenReturn(userResponse);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponse)));
    }

    @Test
    @DisplayName("save returns bad request when user is invalid")
    void saveReturnsBadRequestWhenUserIsInvalid() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("deleteById returns no content when user is deleted")
    void deleteByIdReturnsNoContentWhenUserIsDeleted() throws Exception {
        UUID uuid = UUID.randomUUID();

        doNothing().when(userService).deleteByUuid(uuid);
        mockMvc.perform(delete("/api/v1/user/" + uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("deleteById returns not found when user does not exist")
    void deleteByIdReturnsNotFoundWhenUserDoesNotExist() throws Exception {
        UUID uuid = UUID.randomUUID();

        doThrow(new UserNotFoundException(USER_NOT_FOUND)).when(userService).deleteByUuid(uuid);
        mockMvc.perform(delete("/api/v1/user/" + uuid))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("updateUser returns updated user")
    void updateUserReturnsUpdatedUser() throws Exception {
        UUID uuid = UUID.randomUUID();
        UserPersonalUpdateRequest updateRequest = TestObjectFactory.createUserPersonalUpdateRequest();
        UserResponse updatedUserResponse = TestObjectFactory.createUserResponse();

        when(userService.updateUser(uuid, updateRequest)).thenReturn(updatedUserResponse);

        mockMvc.perform(patch("/api/v1/user/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedUserResponse)));
    }

    @Test
    @DisplayName("updateUser returns not found when user does not exist")
    void updateUserReturnsNotFoundWhenUserDoesNotExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        UserPersonalUpdateRequest updateRequest = TestObjectFactory.createUserPersonalUpdateRequest();

        when(userService.updateUser(uuid, updateRequest)).thenThrow(new UserNotFoundException(USER_NOT_FOUND));

        mockMvc.perform(patch("/api/v1/user/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("updateUser returns bad request when user is invalid")
    void updateUserReturnsBadRequestWhenUserIsInvalid() throws Exception {
        UUID uuid = UUID.randomUUID();
        mockMvc.perform(patch("/api/v1/user/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}