package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.request.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.dto.response.UserResponseDto;
import com.mycarrentals.car_rent.dto.request.UserRoleRequestDto;
import com.mycarrentals.car_rent.exception.RegistrationException;

public interface UserService {
    void updateById(Long id, UserRoleRequestDto role);
    UserResponseDto findById(Long userId);
    void register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
