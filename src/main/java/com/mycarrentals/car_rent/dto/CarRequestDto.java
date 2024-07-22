package com.mycarrentals.car_rent.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRequestDto(
        @NotBlank
        @NotNull
        String model,
        @NotBlank
        @NotNull
        String brand,
        @NotBlank
        @NotNull
        String type,
        @NotNull
        @Min(0)
        int inventory,
        @NotNull
        @Min(0)
        BigDecimal dailyFee) {
}
