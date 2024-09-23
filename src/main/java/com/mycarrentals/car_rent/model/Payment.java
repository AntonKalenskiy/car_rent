package com.mycarrentals.car_rent.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    @Column(name = "rental_id", nullable = false)
    private Long rentalId;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "session_url", length = 512)
    private String sessionUrl;
    @Column(name = "amount_to_pay", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToPay;

    public Payment(Type type, Status status, Long rentalId, String sessionId, String sessionUrl, BigDecimal amountToPay) {
        this.type = type;
        this.status = status;
        this.rentalId = rentalId;
        this.sessionId = sessionId;
        this.sessionUrl = sessionUrl;
        this.amountToPay = amountToPay;
    }

    public enum Status {
        PENDING, PAID
    }

    public enum Type {
        PAYMENT, FINE
    }
}
