package com.mycarrentals.car_rent.dto;

import com.mycarrentals.car_rent.validation.Email;
import com.mycarrentals.car_rent.validation.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        @Size(min = 4, max = 50)
        String email,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Size(min = 4, max = 100)
        String password,
        @NotBlank
        @Size(min = 4, max = 100)
        String repeatPassword) {
}