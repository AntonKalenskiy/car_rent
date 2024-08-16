package com.mycarrentals.car_rent.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
    private Long carId;
    private Long userId;
    private boolean isActive;

    public Rental(Long carId, Long userId, LocalDate actualReturnDate, LocalDate returnDate, LocalDate rentalDate) {
        this.userId = userId;
        this.carId = carId;
        this.actualReturnDate = actualReturnDate;
        this.returnDate = returnDate;
        this.rentalDate = rentalDate;
        this.isActive = true;
    }
}
