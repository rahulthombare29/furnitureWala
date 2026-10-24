package com.furniturewala.backend.controller;

import com.furniturewala.backend.dto.FurnitureTypeRequest;
import com.furniturewala.backend.dto.FurnitureTypeResponse;
import com.furniturewala.backend.service.FurnitureTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/furniture-types")
public class FurnitureTypeController {
    private final FurnitureTypeService service;

    public FurnitureTypeController(FurnitureTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<FurnitureTypeResponse> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FurnitureTypeResponse one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<FurnitureTypeResponse> create(@Valid @RequestBody FurnitureTypeRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));
    }

    @PutMapping("/{id}")
    public FurnitureTypeResponse update(@PathVariable Long id, @Valid @RequestBody FurnitureTypeRequest r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
