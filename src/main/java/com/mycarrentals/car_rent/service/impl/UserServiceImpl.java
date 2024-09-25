package com.mycarrentals.car_rent.service.impl;

import com.mycarrentals.car_rent.dto.request.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.dto.request.UserRequestDto;
import com.mycarrentals.car_rent.dto.response.UserResponseDto;
import com.mycarrentals.car_rent.dto.request.UserRoleRequestDto;
import com.mycarrentals.car_rent.dto.mapper.UserMapper;
import com.mycarrentals.car_rent.exception.RegistrationException;
import com.mycarrentals.car_rent.exception.UserNotFoundException;
import com.mycarrentals.car_rent.model.User;
import com.mycarrentals.car_rent.repository.UserRepository;
import com.mycarrentals.car_rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void updateById(Long id, UserRoleRequestDto role) {
        Set<User.Role> roles = validateRoles(role);
        User userDb = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        userDb.setRoles(roles);
        userRepository.save(userDb);
    }

    @Override
    public UserResponseDto findById(Long userId) {
        return userMapper.toDto(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        ));
    }

    @Override
    public void register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        Optional<User> existingUser = userRepository.findByEmail(requestDto.email());
        if (existingUser.isPresent()) {
            throw new RegistrationException("Unable to complete registration. Email is already in use");
        }
        User user = userMapper.toModel(requestDto);
        user.setRoles(Set.of(User.Role.CUSTOMER));
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found")
        ));
    }

    @Override
    public UserResponseDto updateByEmail(String email, UserRequestDto requestDto) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        userMapper.updateUserFromDto(requestDto, user);
        if (requestDto.password() != null) {
            user.setPassword(passwordEncoder.encode(requestDto.password()));
        }
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    private Set<User.Role> validateRoles(UserRoleRequestDto role) {
        String roles = role.role();
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Illegal argument in request");
        }

        return Arrays.stream(roles.split(","))
                .map(String::trim)
                .map(this::getValidRole)
                .collect(Collectors.toSet());
    }

    private User.Role getValidRole(String role) {
        try {
            return User.Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
