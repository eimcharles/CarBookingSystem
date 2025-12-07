package booking.dao;

import booking.Booking;

import java.util.UUID;

/**
 *      BookingDAO defines the contract for all data
 *      access operations related to Booking entities
 * */

public interface BookingDAO {

    Booking[] getBookings();

    void addBooking(Booking carBooking);

    boolean updateBooking(Booking carBookingToUpdate);

    Booking getBookingById(UUID bookingId);

}
