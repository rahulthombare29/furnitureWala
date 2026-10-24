package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.QuotationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationItemRepository extends JpaRepository<QuotationItem, Long> {
    List<QuotationItem> findByQuotationIdOrderByIdAsc(Long quotationId);
    List<QuotationItem> findByProjectFurnitureId(Long projectFurnitureId);
    long countByQuotationId(Long quotationId);
    void deleteByQuotationId(Long quotationId);
}
