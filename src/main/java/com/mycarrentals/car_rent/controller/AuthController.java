package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.exception.RegistrationException;
import com.mycarrentals.car_rent.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<UserRegistrationRequestDto> register(@RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        userService.register(userRegistrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
