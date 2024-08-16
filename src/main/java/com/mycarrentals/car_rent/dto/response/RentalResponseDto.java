package com.mycarrentals.car_rent.dto.response;

public record RentalResponseDto(
        Long id,
        String rentalDate,
        String returnDate,
        String actualReturnDate,
        CarInfoDto carInfoDto,
        boolean isActive
        ) {
}
