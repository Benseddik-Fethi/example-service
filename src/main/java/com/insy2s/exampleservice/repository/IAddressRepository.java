package com.insy2s.exampleservice.repository;

import com.insy2s.exampleservice.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
