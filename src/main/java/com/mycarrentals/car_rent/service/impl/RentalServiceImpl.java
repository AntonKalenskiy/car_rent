package com.mycarrentals.car_rent.service.impl;

import com.mycarrentals.car_rent.dto.mapper.CarMapper;
import com.mycarrentals.car_rent.dto.mapper.RentalMapper;
import com.mycarrentals.car_rent.dto.request.RentalActualReturnDateRequestDto;
import com.mycarrentals.car_rent.dto.request.RentalRequestDto;
import com.mycarrentals.car_rent.dto.response.CarInfoDto;
import com.mycarrentals.car_rent.dto.response.RentalResponseDto;
import com.mycarrentals.car_rent.exception.CarNotAvailableException;
import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.model.Rental;
import com.mycarrentals.car_rent.repository.CarRepository;
import com.mycarrentals.car_rent.repository.RentalRepository;
import com.mycarrentals.car_rent.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalServiceImpl implements RentalService {
    private final CarRepository carRepository;
    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final CarMapper carMapper;

    @Override
    public RentalResponseDto save(RentalRequestDto rentalRequestDto) {
        Long carId = rentalRequestDto.carId();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id: %d not found!".formatted(carId)));
        checkIfCarAvailable(car);
        car.setInventory(car.getInventory() - 1);
        carRepository.save(car);
        Rental rental = rentalMapper.toModel(rentalRequestDto);
        rental.setCarId(carId);
        rental.setActive(true);
        rental.setUserId(null);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.toDto(savedRental);
    }

    @Override
    public RentalResponseDto findById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rental with id: %d not found!".formatted(id)));
        Long carId = rental.getCarId();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id: %d not found!".formatted(carId)));

        CarInfoDto carInfoDto = carMapper.toDtoDetailedCarInfo(car);
        return rentalMapper.toDto(rental, carInfoDto);
    }

    @Override
    public void setActualReturnDate(Long id, RentalActualReturnDateRequestDto dto) {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rental with id: %d not found!".formatted(id)));
        rental.setActualReturnDate(LocalDate.parse(dto.actualReturnDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd" )));
        rentalRepository.save(rental);
        Long carId = rental.getCarId();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id: %d not found!".formatted(carId)));
        car.setInventory(car.getInventory() + 1);
        carRepository.save(car);
    }

    @Override
    public List<RentalResponseDto> findAllByUserIdAndIsActiveStatus(Long userId, boolean isActive) {
        List<Rental> rentals = rentalRepository.findAllByUserIdAndIsActive(userId, isActive);

        List<CarInfoDto> carResponseDtos = rentals.stream()
                .map(Rental::getCarId)
                .map(carRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(carMapper::toDtoDetailedCarInfo)
                .toList();

        return IntStream.range(0, rentals.size())
                .mapToObj(i -> rentalMapper.toDto(rentals.get(i), carResponseDtos.get(i)))
                .toList();
    }

    private void checkIfCarAvailable(Car car) {
        if (car.getInventory() < 1) {
            throw new CarNotAvailableException("The car is not available");
        }
    }
}
