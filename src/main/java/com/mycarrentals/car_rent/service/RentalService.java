package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.request.RentalActualReturnDateRequestDto;
import com.mycarrentals.car_rent.dto.request.RentalRequestDto;
import com.mycarrentals.car_rent.dto.response.RentalResponseDto;
import java.util.List;

public interface RentalService {
    RentalResponseDto save(RentalRequestDto rentalRequestDto);

    RentalResponseDto findById(Long id);

    void setActualReturnDate(Long id, RentalActualReturnDateRequestDto dto);

    List<RentalResponseDto> findAllByUserIdAndIsActiveStatus(Long id, boolean isActive);
}
