package com.furniturewala.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms", indexes = {
    @Index(name = "idx_rooms_project", columnList = "project_id")
})
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "room_type", nullable = false, length = 40)
    private String roomType;

    @Size(max = 100)
    @Column(name = "room_name", length = 100)
    private String roomName;

    @Positive
    @Column(name = "length_ft", precision = 8, scale = 2)
    private BigDecimal lengthFt;

    @Positive
    @Column(name = "width_ft", precision = 8, scale = 2)
    private BigDecimal widthFt;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
