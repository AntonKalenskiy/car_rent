package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.UserResponseDto;
import com.mycarrentals.car_rent.dto.UserRoleRequestDto;

public interface UserService {
    void updateById(Long id, UserRoleRequestDto role);
    UserResponseDto findById(Long userId);
}
