package com.furniturewala.backend.service.impl;

import com.furniturewala.backend.dto.*;
import com.furniturewala.backend.exception.*;
import com.furniturewala.backend.model.Material;
import com.furniturewala.backend.repository.MaterialRepository;
import com.furniturewala.backend.service.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository repo;

    public MaterialServiceImpl(MaterialRepository repo) {
        this.repo = repo;
    }

    private MaterialResponse map(Material x) {
        return new MaterialResponse(x.getId(), x.getName(), x.getCategory(), x.getPricePerSqft(), x.getDescription(), x.isActive());
    }

    public List<MaterialResponse> findAll() {
        return repo.findByActiveTrueOrderByCategoryAscNameAsc().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public MaterialResponse findById(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Material not found: " + id)));
    }

    public MaterialResponse create(MaterialRequest r) {
        Material x = new Material();
        apply(x, r);
        return map(repo.save(x));
    }

    public MaterialResponse update(Long id, MaterialRequest r) {
        Material x = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Material not found: " + id));
        apply(x, r);
        return map(repo.save(x));
    }

    private void apply(Material x, MaterialRequest r) {
        x.setName(r.name());
        x.setCategory(r.category());
        x.setPricePerSqft(r.pricePerSqft());
        x.setDescription(r.description());
        x.setActive(r.active());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Material not found: " + id);
        repo.deleteById(id);
    }
}
