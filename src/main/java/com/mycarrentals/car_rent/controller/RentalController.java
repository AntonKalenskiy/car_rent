package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.request.RentalActualReturnDateRequestDto;
import com.mycarrentals.car_rent.dto.request.RentalRequestDto;
import com.mycarrentals.car_rent.dto.response.RentalResponseDto;
import com.mycarrentals.car_rent.service.RentalService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalResponseDto> createRental(@RequestBody @Valid RentalRequestDto rentalRequestDto,
                                                          HttpServletResponse response) {
        RentalResponseDto savedRental = rentalService.save(rentalRequestDto);
        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRental.id())
                .toUriString();
        response.setHeader("Location", location);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getById(@PathVariable Long id) {
        RentalResponseDto rentalResponseDto = rentalService.findById(id);
        return ResponseEntity.ok(rentalResponseDto);
    }

    @PostMapping("/{id}/return")
    public void setActualReturnDate(@PathVariable Long id,
                                    @RequestBody @Valid RentalActualReturnDateRequestDto dto) {
        rentalService.setActualReturnDate(id, dto);
    }

    @GetMapping
    public ResponseEntity<List<RentalResponseDto>> getAllByUserIdAndStatus(@RequestParam("user_id") Long userId,
                                                       @RequestParam("is_active") boolean isActive) {
       return ResponseEntity.ok(rentalService.findAllByUserIdAndIsActiveStatus(userId, isActive));
    }
}
