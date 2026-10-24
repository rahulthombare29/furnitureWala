package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    Optional<Quotation> findByQuoteNumber(String quoteNumber);
    List<Quotation> findByProjectIdOrderByVersionDesc(Long projectId);
    List<Quotation> findByStatusOrderByCreatedAtDesc(Quotation.Status status);
    boolean existsByQuoteNumber(String quoteNumber);
}
