package com.furniturewala.backend.dto;

import java.math.BigDecimal;

public record FurnitureTypeResponse(Long id, String name, String category, String description, BigDecimal basePrice,
                                    boolean active) {
}
