package com.furniturewala.backend.service.impl;

import com.furniturewala.backend.dto.*;
import com.furniturewala.backend.exception.*;
import com.furniturewala.backend.model.FurnitureType;
import com.furniturewala.backend.repository.FurnitureTypeRepository;
import com.furniturewala.backend.service.FurnitureTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FurnitureTypeServiceImpl implements FurnitureTypeService {
    private final FurnitureTypeRepository repo;

    public FurnitureTypeServiceImpl(FurnitureTypeRepository repo) {
        this.repo = repo;
    }

    private FurnitureTypeResponse map(FurnitureType x) {
        return new FurnitureTypeResponse(x.getId(), x.getName(), x.getCategory(), x.getDescription(), x.getBasePrice(), x.isActive());
    }

    public List<FurnitureTypeResponse> findAll() {
        return repo.findByActiveTrueOrderByCategoryAscNameAsc().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public FurnitureTypeResponse findById(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("FurnitureType not found: " + id)));
    }

    public FurnitureTypeResponse create(FurnitureTypeRequest r) {
        if (repo.existsByNameIgnoreCaseAndCategoryIgnoreCase(r.name(), r.category()))
            throw new DuplicateResourceException("Furniture type name already exists in category");
        FurnitureType x = new FurnitureType();
        apply(x, r);
        return map(repo.save(x));
    }

    public FurnitureTypeResponse update(Long id, FurnitureTypeRequest r) {
        FurnitureType x = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("FurnitureType not found: " + id));
        apply(x, r);
        return map(repo.save(x));
    }

    private void apply(FurnitureType x, FurnitureTypeRequest r) {
        x.setName(r.name());
        x.setCategory(r.category());
        x.setDescription(r.description());
        x.setBasePrice(r.basePrice());
        x.setActive(r.active());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("FurnitureType not found: " + id);
        repo.deleteById(id);
    }
}
