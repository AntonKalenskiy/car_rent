package com.mycarrentals.car_rent.service.impl;

import com.mycarrentals.car_rent.dto.mapper.PaymentMapper;
import com.mycarrentals.car_rent.dto.request.PaymentRequestDto;
import com.mycarrentals.car_rent.dto.response.PaymentResponseDto;
import com.mycarrentals.car_rent.model.Payment;
import com.mycarrentals.car_rent.model.Rental;
import com.mycarrentals.car_rent.repository.PaymentRepository;
import com.mycarrentals.car_rent.repository.RentalRepository;
import com.mycarrentals.car_rent.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentResponseDto> findAllByUserId(Long userId) {

       return rentalRepository.findByUserId(userId)
                .stream()
                .map(Rental::getId)
                .map(paymentRepository::findByRentalId)
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public PaymentResponseDto createPaymentSession(PaymentRequestDto requestDto) {
        Payment payment = paymentMapper.toModel(requestDto);
        payment.setStatus(Payment.Status.PENDING);
        payment.setSessionUrl(null);
        payment.setSessionId(null);
        Payment paymentDb = paymentRepository.save(payment);
        return paymentMapper.toDto(paymentDb);
    }
}
