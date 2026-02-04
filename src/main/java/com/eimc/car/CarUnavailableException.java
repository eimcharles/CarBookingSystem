package com.eimc.car;

import java.util.UUID;

public class CarUnavailableException extends RuntimeException {

    public CarUnavailableException(UUID carId) {
        super(String.format("Car with registration number '%s' is currently booked and unavailable.", carId));
    }

}

