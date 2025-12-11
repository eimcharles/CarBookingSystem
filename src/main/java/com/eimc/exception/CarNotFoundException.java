package com.eimc.exception;

/**
 *      CarNotFoundException class throws
 *      a custom exception when a
 *      request is made for a Car
 *      that does not exist in the system.
 * */

public class CarNotFoundException extends RuntimeException {

    /**
     *   Constructs a new CarNotFoundException
     *   with a detailed message indicating
     *   the registration number for the car
     *   that is not found.
     * */

    public CarNotFoundException(String registrationNumber) {
        super(String.format("Car with registration number '%s' not found.", registrationNumber));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
