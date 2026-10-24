package com.furniturewala.backend.dto;

import com.furniturewala.backend.model.Project;

import java.math.BigDecimal;
import java.time.Instant;

public record ProjectResponse(Long id, Long customerId, String projectName, Project.HomeType homeType,
                              BigDecimal carpetAreaSqft, String city, String address, Project.Status status,
                              Instant createdAt) {
}
