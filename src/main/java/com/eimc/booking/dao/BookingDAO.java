package com.eimc.booking.dao;

import com.eimc.booking.Booking;

import java.util.List;
import java.util.UUID;

/**
 *      BookingDAO defines the contract for all data
 *      access operations related to Booking entities
 * */

public interface BookingDAO {

    void addBooking(Booking carBooking);

    void removeBooking(Booking carBookingToUpdate);

    Booking getBookingById(UUID bookingId);

    List<Booking> getBookings();
}
