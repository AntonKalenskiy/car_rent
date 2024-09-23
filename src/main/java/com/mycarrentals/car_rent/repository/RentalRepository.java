package com.mycarrentals.car_rent.repository;

import com.mycarrentals.car_rent.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByUserIdAndIsActive(Long userId, boolean isActive);

    List<Rental> findByUserId(Long userId);
}
