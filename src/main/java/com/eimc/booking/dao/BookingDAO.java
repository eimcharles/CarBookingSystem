package com.eimc.booking.dao;

import com.eimc.booking.Booking;

import java.util.UUID;

/**
 *      BookingDAO defines the contract for all data
 *      access operations related to Booking entities
 * */

public interface BookingDAO {

    Booking[] getBookings();

    void addBooking(Booking carBooking);

    void updateBooking(Booking carBookingToUpdate);

    Booking getBookingById(UUID bookingId);

}
