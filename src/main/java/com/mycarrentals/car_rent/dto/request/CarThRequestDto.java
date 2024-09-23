package com.mycarrentals.car_rent.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CarThRequestDto {
    private Long id;
    @NotBlank(message = "Model is required")
    private String model;
    @NotBlank(message = "Brand is required")
    private String brand;
    @NotBlank(message = "Car type is required")
    private String type;
    @Min(value = 1, message = "Inventory must be at least 1")
    private Integer inventory;
    @NotNull(message = "Daily fee must be a positive value")
    @Min(value = 0, message = "Daily fee must be a positive value")
    private BigDecimal dailyFee;
}
