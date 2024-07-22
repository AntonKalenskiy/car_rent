package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.dto.UserResponseDto;
import com.mycarrentals.car_rent.dto.UserRoleRequestDto;
import com.mycarrentals.car_rent.exception.RegistrationException;

public interface UserService {
    void updateById(Long id, UserRoleRequestDto role);
    UserResponseDto findById(Long userId);
    void register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
