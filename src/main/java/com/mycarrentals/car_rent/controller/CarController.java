package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.CarRequestDto;
import com.mycarrentals.car_rent.dto.CarResponseDto;
import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.service.CarService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarRequestDto carRequestDto,
                                         HttpServletResponse response) {
        CarResponseDto savedCar = carService.save(carRequestDto);
        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.id())
                .toUriString();
        response.setHeader("Location", location);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> getAllCars() {
        List<CarResponseDto> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable Long id) {
        CarResponseDto car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDto> updateEntireCar(@PathVariable Long id,
                                                          @RequestBody CarRequestDto carRequestDto) {
        carService.updateById(id, carRequestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable Long id,
                                            @RequestBody CarRequestDto carRequestDto) {
        carService.updateById(id, carRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarResponseDto> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
