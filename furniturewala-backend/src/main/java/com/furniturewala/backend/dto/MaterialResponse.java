package com.furniturewala.backend.dto;

import java.math.BigDecimal;

public record MaterialResponse(Long id, String name, String category, BigDecimal pricePerSqft, String description,
                               boolean active) {
}
