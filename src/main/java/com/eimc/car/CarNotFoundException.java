package com.eimc.car;

import java.util.UUID;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(UUID carId) {
        super(String.format("Car with registration number '%s' not found.", carId));
    }

}
