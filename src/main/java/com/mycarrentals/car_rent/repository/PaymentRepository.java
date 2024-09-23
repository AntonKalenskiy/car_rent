package com.mycarrentals.car_rent.repository;

import com.mycarrentals.car_rent.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByRentalId(Long rentalId);
}
