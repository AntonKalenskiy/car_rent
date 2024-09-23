package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.request.PaymentRequestDto;
import com.mycarrentals.car_rent.dto.response.PaymentResponseDto;
import com.mycarrentals.car_rent.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllByUserId(@RequestParam("user_id") Long userId) {
        return ResponseEntity.ok(paymentService.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPaymentSession(@RequestBody PaymentRequestDto requestDto) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(paymentService.createPaymentSession(requestDto));
    }

}
