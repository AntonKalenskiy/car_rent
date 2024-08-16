package com.mycarrentals.car_rent.validation;

import com.mycarrentals.car_rent.dto.request.RentalRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalBeforeReturnDateValidator implements ConstraintValidator<RentalBeforeReturnDate, RentalRequestDto> {

    @Override
    public boolean isValid(RentalRequestDto dto, ConstraintValidatorContext context) {
        LocalDate rentalDate = LocalDate.parse(dto.rentalDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate returnDate = LocalDate.parse(dto.returnDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        return rentalDate.isBefore(returnDate) || rentalDate.isEqual(returnDate);
    }
}
