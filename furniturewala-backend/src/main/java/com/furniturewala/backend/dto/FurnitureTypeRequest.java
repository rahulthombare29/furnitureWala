package com.furniturewala.backend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record FurnitureTypeRequest(@NotBlank @Size(max = 120) String name, @NotBlank @Size(max = 60) String category,
                                   String description, @NotNull @PositiveOrZero BigDecimal basePrice, boolean active) {
}
