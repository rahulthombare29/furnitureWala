package com.furniturewala.backend.dto;

import com.furniturewala.backend.model.Project;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProjectRequest(@NotNull Long customerId, @NotBlank @Size(max = 150) String projectName,
                             @NotNull Project.HomeType homeType, @Positive BigDecimal carpetAreaSqft,
                             @Size(max = 100) String city, String address, Project.Status status) {
}
