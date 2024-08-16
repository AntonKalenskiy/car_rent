package com.mycarrentals.car_rent.dto.mapper;

import com.mycarrentals.car_rent.config.MapperConfig;
import com.mycarrentals.car_rent.dto.response.CarInfoDto;
import com.mycarrentals.car_rent.dto.request.RentalRequestDto;
import com.mycarrentals.car_rent.dto.response.RentalResponseDto;
import com.mycarrentals.car_rent.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CarMapper.class)
public interface RentalMapper {

    @Mapping(source = "rentalDate", target = "rentalDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "returnDate", target = "returnDate", dateFormat = "yyyy-MM-dd")
    Rental toModel(RentalRequestDto rentalRequestDto);

    @Mapping(source = "active", target = "isActive")
    RentalResponseDto toDto(Rental rental);

    @Mapping(source = "carInfoDto", target = "carInfoDto")
    @Mapping(source = "rental.active", target = "isActive")
    RentalResponseDto toDto(Rental rental, CarInfoDto carInfoDto);
}
