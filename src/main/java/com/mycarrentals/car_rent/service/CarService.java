package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.CarRequestDto;
import com.mycarrentals.car_rent.dto.CarResponseDto;

import java.util.List;

public interface CarService {
    CarResponseDto save(CarRequestDto requestDto);

    List<CarResponseDto> findAllCars ();

    CarResponseDto findById(Long carId);

    void updateById(Long id, CarRequestDto carRequestDto);

    void deleteById(Long id);
}
