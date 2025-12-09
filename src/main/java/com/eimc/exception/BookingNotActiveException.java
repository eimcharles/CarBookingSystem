package com.eimc.exception;

import java.util.UUID;

/**
 *      BookingUnavailableException class throws
 *      a custom exception when a
 *      request is made for a Booking that
 *      is currently not active.
 * */

public class BookingNotActiveException extends RuntimeException {

    /**
     *   Constructs a new BookingNotActiveException
     *   with a detailed message indicating the
     *   booking currently not active.
     * */

    public BookingNotActiveException(UUID bookingId) {
        super(String.format("Booking with booking id '%s' is currently not active (already cancelled).", bookingId));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
