package com.mycarrentals.car_rent.dto.response;

import java.math.BigDecimal;

public record CarInfoDto(
        String model,
        String brand,
        String type,
        BigDecimal dailyFee
) {
}
