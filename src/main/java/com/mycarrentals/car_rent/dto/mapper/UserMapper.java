package com.mycarrentals.car_rent.dto.mapper;

import com.mycarrentals.car_rent.config.MapperConfig;
import com.mycarrentals.car_rent.dto.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.dto.UserResponseDto;
import com.mycarrentals.car_rent.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "isDeleted", constant = "false")
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
