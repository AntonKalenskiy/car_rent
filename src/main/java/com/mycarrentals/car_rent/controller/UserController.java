package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.request.UserRequestDto;
import com.mycarrentals.car_rent.dto.request.UserRoleRequestDto;
import com.mycarrentals.car_rent.dto.response.UserResponseDto;
import com.mycarrentals.car_rent.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.mycarrentals.car_rent.model.User;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    public void updateUserRole(@PathVariable Long id,
                               @RequestBody @Valid UserRoleRequestDto role) {
        userService.updateById(id, role);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserResponseDto user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto> updateUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                                          @RequestBody UserRequestDto requestDto) {
        String email = userDetails.getUsername();
        UserResponseDto user = userService.updateByEmail(email, requestDto);
        return ResponseEntity.ok(user);
    }
}
