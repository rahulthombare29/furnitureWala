package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.FurnitureType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FurnitureTypeRepository extends JpaRepository<FurnitureType, Long> {
    List<FurnitureType> findByActiveTrueOrderByCategoryAscNameAsc();
    List<FurnitureType> findByCategoryIgnoreCaseAndActiveTrueOrderByNameAsc(String category);
    List<FurnitureType> findByNameContainingIgnoreCaseAndActiveTrueOrderByNameAsc(String name);
    boolean existsByNameIgnoreCaseAndCategoryIgnoreCase(String name, String category);
}
