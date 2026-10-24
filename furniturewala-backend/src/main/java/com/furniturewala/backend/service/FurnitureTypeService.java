package com.furniturewala.backend.service;

import com.furniturewala.backend.dto.*;

import java.util.*;

public interface FurnitureTypeService {
    List<FurnitureTypeResponse> findAll();

    FurnitureTypeResponse findById(Long id);

    FurnitureTypeResponse create(FurnitureTypeRequest r);

    FurnitureTypeResponse update(Long id, FurnitureTypeRequest r);

    void delete(Long id);
}
