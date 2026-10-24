package com.furniturewala.backend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record MaterialRequest(@NotBlank @Size(max = 120) String name, @NotBlank @Size(max = 60) String category,
                              @NotNull @PositiveOrZero BigDecimal pricePerSqft, String description, boolean active) {
}
