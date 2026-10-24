package com.furniturewala.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "quotation_items", indexes = {
    @Index(name = "idx_quotation_items_quote", columnList = "quotation_id")
})
@Getter
@Setter
@NoArgsConstructor
public class QuotationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_furniture_id")
    private ProjectFurniture projectFurniture;

    @NotBlank
    @Size(max = 150)
    @Column(name = "item_name", nullable = false, length = 150)
    private String itemName;

    @Size(max = 120)
    @Column(name = "material_name", length = 120)
    private String materialName;

    @Positive
    @Column(nullable = false)
    private Integer quantity = 1;

    @PositiveOrZero
    @Column(name = "unit_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "line_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal lineTotal = BigDecimal.ZERO;

    @PrePersist
    protected void applyDefaults() {
        if (quantity == null) quantity = 1;
        if (unitPrice == null) unitPrice = BigDecimal.ZERO;
        if (lineTotal == null) lineTotal = BigDecimal.ZERO;
    }
}
