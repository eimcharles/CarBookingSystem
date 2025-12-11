package com.eimc.exception;

import java.util.UUID;

/**
 *      BookingNotFoundException class throws
 *      a custom exception when a
 *      request is made for a Booking
 *      that does not exist in the system.
 * */

public class BookingNotFoundException extends RuntimeException {

    /**
     *   Constructs a new BookingNotFoundException
     *   with a detailed message indicating
     *   the registration number for the car
     *   that is not found.
     * */

    public BookingNotFoundException(UUID bookingNumber) {
        super(String.format("Booking with booking number '%s' not found.", bookingNumber));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
