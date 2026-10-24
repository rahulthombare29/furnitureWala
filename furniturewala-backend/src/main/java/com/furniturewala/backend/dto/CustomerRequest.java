package com.furniturewala.backend.dto;

import jakarta.validation.constraints.*;

public record CustomerRequest(@NotBlank @Size(max = 150) String fullName, @NotBlank @Size(max = 20) String phone,
                              @Email @Size(max = 255) String email) {
}
