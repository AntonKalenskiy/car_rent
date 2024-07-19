package com.mycarrentals.car_rent.service.impl;

import com.mycarrentals.car_rent.dto.UserResponseDto;
import com.mycarrentals.car_rent.dto.UserRoleRequestDto;
import com.mycarrentals.car_rent.dto.mapper.UserMapper;
import com.mycarrentals.car_rent.exception.UserNotFoundException;
import com.mycarrentals.car_rent.model.User;
import com.mycarrentals.car_rent.repository.UserRepository;
import com.mycarrentals.car_rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void updateById(Long id, UserRoleRequestDto role) {
        validateRoleExisting(role);
        User userDb = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        userDb.setRole(User.Role.valueOf(role.role().toUpperCase()));
        userRepository.save(userDb);
    }

    @Override
    public UserResponseDto findById(Long userId) {
        return userMapper.toDto(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        ));
    }

    private void validateRoleExisting(UserRoleRequestDto role) {
        if(Arrays.stream(User.Role.values()).noneMatch(e -> e.name().equals(role.role().toUpperCase()))) {
            throw new IllegalArgumentException("Illegal argument in request");
        }
    }
}
