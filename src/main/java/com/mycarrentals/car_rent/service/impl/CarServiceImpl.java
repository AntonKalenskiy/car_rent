package com.mycarrentals.car_rent.service.impl;

import com.mycarrentals.car_rent.dto.CarRequestDto;
import com.mycarrentals.car_rent.dto.CarResponseDto;
import com.mycarrentals.car_rent.dto.mapper.CarMapper;
import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.repository.CarRepository;
import com.mycarrentals.car_rent.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarResponseDto save(CarRequestDto requestDto) {
       Car car = carMapper.toModel(requestDto);
       Car savedCar = carRepository.save(car);
       return carMapper.toDto(savedCar);
    }

    @Override
    public List<CarResponseDto> findAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .toList();
    }

    @Override
    public CarResponseDto findById(Long carId) {
        return carMapper.toDto(carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id: %d not found!".formatted(carId))));
    }

    @Override
    public void updateById(Long carId, CarRequestDto carRequestDto) {
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id: %d not found!".formatted(carId)));
        carMapper.updateCarFromDto(carRequestDto, car);
        carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
