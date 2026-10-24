package com.furniturewala.backend.service;

import com.furniturewala.backend.dto.*;

import java.util.*;

public interface CustomerService {
    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
