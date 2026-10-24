package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByActiveTrueOrderByCategoryAscNameAsc();
    List<Material> findByCategoryIgnoreCaseAndActiveTrueOrderByNameAsc(String category);
    List<Material> findByNameContainingIgnoreCaseAndActiveTrueOrderByNameAsc(String name);
}
