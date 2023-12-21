package com.insy2s.exampleservice.service.impl;

import com.insy2s.exampleservice.domain.User;
import com.insy2s.exampleservice.dto.mapper.IUserMapper;
import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;
import com.insy2s.exampleservice.error.exception.UserNotFoundException;
import com.insy2s.exampleservice.repository.IUserRepository;
import com.insy2s.exampleservice.util.TestObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@ActiveProfiles("test")
class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll returns all users")
    void findAllReturnsAllUsers() {
        // Given
        User user1 = TestObjectFactory.createUser();
        User user2 = TestObjectFactory.createUser();
        List<User> users = Arrays.asList(user1, user2);
        UserResponse userResponse1 = TestObjectFactory.createUserResponse();
        UserResponse userResponse2 = TestObjectFactory.createUserResponse();
        List<UserResponse> userResponses = Arrays.asList(userResponse1, userResponse2);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(user1)).thenReturn(userResponse1);
        when(userMapper.toDto(user2)).thenReturn(userResponse2);
        // When
        List<UserResponse> result = userService.findAll();
        // Then
        assertEquals(userResponses, result);
    }

    @Test
    @DisplayName("findAll returns empty list when no users")
    void findAllReturnsEmptyListWhenNoUsers() {
        List<User> users = List.of();
        List<UserResponse> userResponses = List.of();
        when(userRepository.findAll()).thenReturn(users);
        List<UserResponse> result = userService.findAll();
        assertEquals(userResponses, result);
    }

    @Test
    @DisplayName("findById returns user when user exists")
    void findByIdTest() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(TestObjectFactory.createUser()));
        when(userMapper.toDto(any())).thenReturn(TestObjectFactory.createUserResponse());

        UserResponse response = userService.findById(uuid);

        assertNotNull(response);
        verify(userRepository).findByUuid(uuid);
        verify(userMapper).toDto(any());
    }

    @Test
    @DisplayName("findById throws UserNotFoundException when user does not exist")
    void findByIdNotFoundTest() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.findById(uuid));
    }

    @Test
    @DisplayName("save returns saved user")
    void saveTest() {
        UserRequest userRequest = TestObjectFactory.createUserRequest();
        when(userMapper.toEntity(userRequest)).thenReturn(TestObjectFactory.createUser());
        when(userRepository.save(any())).thenReturn(TestObjectFactory.createUser());
        when(userMapper.toDto(any())).thenReturn(TestObjectFactory.createUserResponse());
        UserResponse savedUser = userService.save(userRequest);
        assertNotNull(savedUser);
        verify(userRepository).save(any());
        verify(userMapper).toEntity(any(UserRequest.class));
        verify(userMapper).toDto(any());
    }

    @Test
    @DisplayName("deleteByUuid deletes user when user exists")
    void deleteByUuidTest() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(TestObjectFactory.createUser()));
        userService.deleteByUuid(uuid);
        verify(userRepository).findByUuid(uuid);
        verify(userRepository).delete(any());
    }

    @Test
    @DisplayName("deleteByUuid throws UserNotFoundException when user does not exist")
    void deleteByUuidNotFoundTest() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteByUuid(uuid));
    }

    @Test
    @DisplayName("updateUser updates user when user exists")
    void updateUserTest() {
        UUID uuid = UUID.randomUUID();
        UserPersonalUpdateRequest updateRequest = TestObjectFactory.createUserPersonalUpdateRequest();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(TestObjectFactory.createUser()));
        when(userMapper.partialUpdate(updateRequest, TestObjectFactory.createUser())).thenReturn(TestObjectFactory.createUser());
        when(userRepository.save(any())).thenReturn(TestObjectFactory.createUser());
        when(userMapper.toDto(any())).thenReturn(TestObjectFactory.createUserResponse());
        UserResponse updatedUser = userService.updateUser(uuid, updateRequest);
        assertNotNull(updatedUser);
        verify(userRepository).findByUuid(uuid);
        verify(userMapper).partialUpdate(any(UserPersonalUpdateRequest.class), any());
        verify(userRepository).save(any());
        verify(userMapper).toDto(any());
    }

    @Test
    @DisplayName("updateUser throws UserNotFoundException when user does not exist")
    void updateUserNotFoundTest() {
        UUID uuid = UUID.randomUUID();
        UserPersonalUpdateRequest updateRequest = TestObjectFactory.createUserPersonalUpdateRequest();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(uuid, updateRequest));
    }

}