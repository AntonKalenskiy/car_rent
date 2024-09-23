package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.request.CarRequestDto;
import com.mycarrentals.car_rent.dto.request.CarThRequestDto;
import com.mycarrentals.car_rent.dto.response.CarResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarResponseDto save(CarRequestDto requestDto);

    CarResponseDto saveCarTh(CarThRequestDto carThRequestDto);

    List<CarResponseDto> findAllCars (Pageable pageable);

    List<CarResponseDto> findAllCars ();

    CarResponseDto findById(Long carId);

    void updateById(Long id, CarRequestDto carRequestDto);

    void deleteById(Long id);
}
