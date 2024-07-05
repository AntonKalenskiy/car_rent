package com.mycarrentals.car_rent.dto.mapper;

import com.mycarrentals.car_rent.config.MapperConfig;
import com.mycarrentals.car_rent.dto.CarRequestDto;
import com.mycarrentals.car_rent.dto.CarResponseDto;
import com.mycarrentals.car_rent.model.Car;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface CarMapper {

    @Mapping(target = "type", qualifiedByName = "carTypeToString")
    CarResponseDto toDto(Car car);

    @Mapping(target = "type", qualifiedByName = "stringToCarType")
    Car toModel(CarRequestDto carRequestDto);

    @Named("stringToCarType")
    default Car.CarType stringToCarType(String carType) {
        return Car.CarType.valueOf(carType);
    }

    @Named("carTypeToString")
    default String carTypeToString(Car.CarType carType) {
        return carType.name();
    }

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "model", source = "model",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "brand", source = "brand",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "type", source = "type",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "inventory", source = "inventory",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "dailyFee", source = "dailyFee",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    })
    void updateCarFromDto(CarRequestDto carRequestDto, @MappingTarget Car car);
}
