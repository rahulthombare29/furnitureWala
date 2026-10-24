package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByEmailIgnoreCase(String email);
    List<Customer> findByFullNameContainingIgnoreCase(String fullName);
    boolean existsByPhone(String phone);
    boolean existsByEmailIgnoreCase(String email);
}
