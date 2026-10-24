package com.furniturewala.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "projects", indexes = {
    @Index(name = "idx_projects_customer", columnList = "customer_id")
})
@Getter
@Setter
@NoArgsConstructor
public class Project {
    public enum HomeType { ONE_BHK, TWO_BHK, THREE_BHK, FOUR_BHK, OTHER }
    public enum Status { DRAFT, ESTIMATING, QUOTED, APPROVED, IN_PROGRESS, COMPLETED, CANCELLED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank
    @Size(max = 150)
    @Column(name = "project_name", nullable = false, length = 150)
    private String projectName;

    @NotNull
    @Convert(converter = HomeTypeConverter.class)
    @Column(name = "home_type", nullable = false, length = 20)
    private HomeType homeType;

    @Positive
    @Column(name = "carpet_area_sqft", precision = 10, scale = 2)
    private BigDecimal carpetAreaSqft;

    @Size(max = 100)
    @Column(length = 100)
    private String city;

    @Column(columnDefinition = "TEXT")
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Status status = Status.DRAFT;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (status == null) status = Status.DRAFT;
    }
}
