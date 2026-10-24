package com.furniturewala.backend.service;

import com.furniturewala.backend.dto.*;

import java.util.*;

public interface MaterialService {
    List<MaterialResponse> findAll();

    MaterialResponse findById(Long id);

    MaterialResponse create(MaterialRequest r);

    MaterialResponse update(Long id, MaterialRequest r);

    void delete(Long id);
}
