package com.mycarrentals.car_rent.dto.request;

import java.math.BigDecimal;

public record PaymentRequestDto(
        Long rentalId,
        BigDecimal amountToPay,
        String paymentType
) {
}
