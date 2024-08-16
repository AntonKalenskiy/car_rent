package com.mycarrentals.car_rent.validation;

import jakarta.validation.Constraint;
import java.lang.annotation.Target;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = RentalBeforeReturnDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RentalBeforeReturnDate {
    String message() default "Rental date must be before return date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}