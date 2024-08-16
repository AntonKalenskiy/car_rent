package com.mycarrentals.car_rent.exception;

public class CarNotAvailableException extends RuntimeException{
    public CarNotAvailableException(String message) {
        super(message);
    }
}
