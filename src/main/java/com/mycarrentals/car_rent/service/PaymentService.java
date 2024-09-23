package com.mycarrentals.car_rent.service;

import com.mycarrentals.car_rent.dto.request.PaymentRequestDto;
import com.mycarrentals.car_rent.dto.response.PaymentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PaymentService {

    List<PaymentResponseDto> findAllByUserId(Long userId);

    PaymentResponseDto createPaymentSession(PaymentRequestDto requestDto);
}
