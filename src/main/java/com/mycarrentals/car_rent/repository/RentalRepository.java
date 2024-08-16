package com.mycarrentals.car_rent.repository;

import com.mycarrentals.car_rent.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByUserIdAndIsActive(Long userId, boolean isActive);
}
