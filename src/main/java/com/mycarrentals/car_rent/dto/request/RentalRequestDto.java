package com.mycarrentals.car_rent.dto.request;

import com.mycarrentals.car_rent.validation.RentalBeforeReturnDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RentalBeforeReturnDate
public record RentalRequestDto(
        @Min(value = 0)
        @NotNull
        Long carId,
        @NotBlank(message = "Rental date can not be null or blank")
        String rentalDate,
        @NotBlank(message = "Return date can not be null or blank")
        String returnDate) {
}
