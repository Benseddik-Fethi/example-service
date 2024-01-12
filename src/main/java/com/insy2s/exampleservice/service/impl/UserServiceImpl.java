package com.insy2s.exampleservice.service.impl;

import com.insy2s.exampleservice.dto.mapper.IUserMapper;
import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;
import com.insy2s.exampleservice.error.exception.UserNotFoundException;
import com.insy2s.exampleservice.repository.IUserRepository;
import com.insy2s.exampleservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.insy2s.exampleservice.error.record.ErrorMessage.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(final UUID uuid) {
        return userRepository.findByUuid(uuid)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse save(final UserRequest userRequest) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userRequest)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByUuid(final UUID uuid) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse updateUser(final UUID uuid, final UserPersonalUpdateRequest request) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user = userMapper.partialUpdate(request, user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

}
