package com.mycarrentals.car_rent.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.NonFinal;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SQLDelete(sql = "UPDATE cars SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
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
    @ToString.Exclude
    private boolean isDeleted;

    public Car(String model, String brand, CarType type, int inventory, BigDecimal dailyFee, boolean isDeleted) {
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.inventory = inventory;
        this.dailyFee = dailyFee;
        this.isDeleted = isDeleted;
    }

    public enum CarType{
        SEDAN, SUV, HATCHBACK, ESTATE;
    }
}
