package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.UserRoleRequestDto;
import com.mycarrentals.car_rent.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUserInfo() {
//        userService
        return null;
    }


}
