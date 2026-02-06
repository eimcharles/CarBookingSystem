package com.eimc.car.exception;

import java.util.UUID;

public class CarCurrentlyBookedException extends RuntimeException {

    public CarCurrentlyBookedException(UUID carId) {
        super(String.format("Car with registration number '%s' is currently booked.", carId));
    }

}
