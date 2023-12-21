package com.insy2s.exampleservice.repository;

import com.insy2s.exampleservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, Integer> {


    /**
     * Retrieves a user by uuid.
     * @param uuid The uuid of the user to retrieve.
     * @return A User object representing the user.
     */
    Optional<User> findByUuid(UUID uuid);
}