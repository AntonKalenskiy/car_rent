package com.mycarrentals.car_rent.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRoleRequestDto(
        @NotBlank(message = "Role can not be blank or null")
        String role) {
}
