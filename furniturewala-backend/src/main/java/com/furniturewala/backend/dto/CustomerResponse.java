package com.furniturewala.backend.dto;

import java.time.Instant;

public record CustomerResponse(Long id, String fullName, String phone, String email, Instant createdAt) {
}
