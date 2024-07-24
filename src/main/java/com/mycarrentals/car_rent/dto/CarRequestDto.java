package com.mycarrentals.car_rent.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CarRequestDto(
        @NotBlank(message = "Model should not be blank or null")
        String model,
        @NotBlank(message = "Brand should not be blank or null")
        String brand,
        @NotBlank(message = "Type should not be blank or null")
        String type,
        @Min(value = 0, message = "Inventory must be greater than or equal to 0")
        int inventory,
        @NotNull
        BigDecimal dailyFee) {
}
