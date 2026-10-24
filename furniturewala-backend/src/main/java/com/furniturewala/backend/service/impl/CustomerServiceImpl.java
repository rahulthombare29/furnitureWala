package com.furniturewala.backend.service.impl;

import com.furniturewala.backend.dto.*;
import com.furniturewala.backend.exception.*;
import com.furniturewala.backend.model.Customer;
import com.furniturewala.backend.repository.CustomerRepository;
import com.furniturewala.backend.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    private CustomerResponse map(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFullName(), customer.getPhone(), customer.getEmail(), customer.getCreatedAt());
    }

    public List<CustomerResponse> findAll() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id)));
    }

    public CustomerResponse create(CustomerRequest r) {
        if (repo.existsByPhone(r.phone())) throw new DuplicateResourceException("Phone already exists");
        if (r.email() != null && !r.email().isBlank() && repo.existsByEmailIgnoreCase(r.email()))
            throw new DuplicateResourceException("Email already exists");
        Customer x = new Customer();
        apply(x, r);
        return map(repo.save(x));
    }

    public CustomerResponse update(Long id, CustomerRequest r) {
        Customer x = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        repo.findByPhone(r.phone()).filter(c -> !c.getId().equals(id)).ifPresent(c -> {
            throw new DuplicateResourceException("Phone already exists");
        });
        if (r.email() != null && !r.email().isBlank())
            repo.findByEmailIgnoreCase(r.email()).filter(c -> !c.getId().equals(id)).ifPresent(c -> {
                throw new DuplicateResourceException("Email already exists");
            });
        apply(x, r);
        return map(repo.save(x));
    }

    private void apply(Customer customer, CustomerRequest r) {
        customer.setFullName(r.fullName().trim());
        customer.setPhone(r.phone().trim());
        customer.setEmail(r.email() == null || r.email().isBlank() ? null : r.email().trim());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Customer not found: " + id);
        repo.deleteById(id);
    }
}
