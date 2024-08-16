package com.mycarrentals.car_rent.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RentalActualReturnDateRequestDto(
        @NotBlank(message = "Actual return date should not be blank or null")
        String actualReturnDate) {
}
