package com.mycarrentals.car_rent.dto.mapper;

import com.mycarrentals.car_rent.config.MapperConfig;
import com.mycarrentals.car_rent.dto.request.PaymentRequestDto;
import com.mycarrentals.car_rent.dto.response.PaymentResponseDto;
import com.mycarrentals.car_rent.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PaymentMapper {

    PaymentResponseDto toDto(Payment payment);

    @Mapping(source = "paymentType", target = "type")
    Payment toModel(PaymentRequestDto requestDto);
}
