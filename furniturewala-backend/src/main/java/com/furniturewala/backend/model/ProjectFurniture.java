package com.furniturewala.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "project_furniture", indexes = {
    @Index(name = "idx_project_furniture_room", columnList = "room_id")
})
@Getter
@Setter
@NoArgsConstructor
public class ProjectFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "furniture_type_id", nullable = false)
    private FurnitureType furnitureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantity = 1;

    @Positive
    @Column(name = "width_ft", precision = 8, scale = 2)
    private BigDecimal widthFt;

    @Positive
    @Column(name = "height_ft", precision = 8, scale = 2)
    private BigDecimal heightFt;

    @Positive
    @Column(name = "depth_ft", precision = 8, scale = 2)
    private BigDecimal depthFt;

    @Column(name = "customization_notes", columnDefinition = "TEXT")
    private String customizationNotes;

    @PositiveOrZero
    @Column(name = "estimated_unit_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal estimatedUnitPrice = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "estimated_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal estimatedTotal = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (quantity == null) quantity = 1;
        if (estimatedUnitPrice == null) estimatedUnitPrice = BigDecimal.ZERO;
        if (estimatedTotal == null) estimatedTotal = BigDecimal.ZERO;
    }
}
