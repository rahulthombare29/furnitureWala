package com.furniturewala.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "quotations",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_quotations_quote_number", columnNames = "quote_number"),
        @UniqueConstraint(name = "uk_quotations_project_version", columnNames = {"project_id", "version"})
    },
    indexes = {
        @Index(name = "idx_quotations_project", columnList = "project_id")
    })
@Getter
@Setter
@NoArgsConstructor
public class Quotation {
    public enum Status { DRAFT, SENT, ACCEPTED, REJECTED, EXPIRED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotBlank
    @Size(max = 40)
    @Column(name = "quote_number", nullable = false, unique = true, length = 40)
    private String quoteNumber;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer version = 1;

    @PositiveOrZero
    @Column(name = "material_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal materialTotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "labor_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal laborTotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "installation_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal installationTotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "tax_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxTotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "grand_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal grandTotal = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.DRAFT;

    @Column(name = "valid_until")
    private LocalDate validUntil;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (version == null) version = 1;
        if (status == null) status = Status.DRAFT;
        if (materialTotal == null) materialTotal = BigDecimal.ZERO;
        if (laborTotal == null) laborTotal = BigDecimal.ZERO;
        if (installationTotal == null) installationTotal = BigDecimal.ZERO;
        if (discount == null) discount = BigDecimal.ZERO;
        if (taxTotal == null) taxTotal = BigDecimal.ZERO;
        if (grandTotal == null) grandTotal = BigDecimal.ZERO;
    }
}
