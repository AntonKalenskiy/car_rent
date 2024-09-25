package com.mycarrentals.car_rent.dto.mapper;

import com.mycarrentals.car_rent.config.MapperConfig;
import com.mycarrentals.car_rent.dto.request.UserRegistrationRequestDto;
import com.mycarrentals.car_rent.dto.request.UserRequestDto;
import com.mycarrentals.car_rent.dto.response.UserResponseDto;
import com.mycarrentals.car_rent.model.User;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "role", expression = "java(String.join(\",\", user.getRoles().stream().map(Enum::name).toList()))")
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "email", source = "email",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "firstName", source = "firstName",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "lastName", source = "lastName",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(target = "password", ignore = true)
    })
    void updateUserFromDto(UserRequestDto requestDto, @MappingTarget User user);
}
