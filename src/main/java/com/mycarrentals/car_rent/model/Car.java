package com.mycarrentals.car_rent.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String brand;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType type;
    private int inventory;
    private BigDecimal dailyFee;
    private boolean isDeleted;

    public enum CarType{
        SEDAN, SUV, HATCHBACK, ESTATE;
    }

    public Car() {}

    public Car(String model, String brand, CarType type, int inventory, BigDecimal dailyFee, boolean isDeleted) {
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.inventory = inventory;
        this.dailyFee = dailyFee;
        this.isDeleted = isDeleted;
    }
}
