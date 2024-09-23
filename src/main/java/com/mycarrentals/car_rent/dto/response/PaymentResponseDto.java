package com.mycarrentals.car_rent.dto.response;

import java.math.BigDecimal;

public record PaymentResponseDto(
        Long id,
        String status,
        String type,
        Long rentalId,
        String sessionId,
        String sessionUrl,
        BigDecimal amountToPay
) {
}
