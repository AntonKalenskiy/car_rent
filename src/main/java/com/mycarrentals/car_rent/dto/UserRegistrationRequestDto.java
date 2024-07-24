package com.mycarrentals.car_rent.dto;

import com.mycarrentals.car_rent.validation.Email;
import com.mycarrentals.car_rent.validation.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        @Size(min = 4, max = 50, message = "Invalid email address.")
        String email,
        @NotBlank(message = "First name should not be blank or null")
        String firstName,
        @NotBlank(message = "Last name should not be blank or null")
        String lastName,
        @NotBlank
        @Size(min = 8, max = 20, message = "Invalid password. Size must be from 8 chars.")
        String password,
        @NotBlank
        @Size(min = 8, max = 20, message = "Invalid repeat password. Size must be from 8 chars.")
        String repeatPassword) {
}