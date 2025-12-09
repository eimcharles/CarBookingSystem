package com.eimc.exception;

/**
 *      CarUnavailableException class throws
 *      a custom exception when a
 *      request is made for a Car that
 *      is currently booked by another user.
 * */

public class CarUnavailableException extends RuntimeException {

    /**
     *   Constructs a new CarUnavailableException
     *   with a detailed message indicating
     *   the registration number for the car
     *   that is currently booked by another user
     * */

    public CarUnavailableException(String registrationNumber) {
        super(String.format("Car with registration number '%s' is currently booked and unavailable.", registrationNumber));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

