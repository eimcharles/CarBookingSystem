package com.eimc.booking.dao;

import com.eimc.booking.Booking;

import com.eimc.exception.BookingNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListBookingDAO implements BookingDAO {

    private final List<Booking> bookingsDao = new ArrayList<>();

    public ListBookingDAO() {}

    @Override
    public void addBooking(Booking carBooking) {

        ///  Check if null
        if (carBooking == null)
            throw new IllegalArgumentException("Booking cannot be null");

        ///  Add booking
        bookingsDao.add(carBooking);
    }

    @Override
    public void removeBooking(Booking carBookingToUpdate) {

        ///  Target booking to be removed
        UUID targetId = carBookingToUpdate.getUserBookingID();

        /// Check the booking state
        if (carBookingToUpdate.isBookingActive()) {
            throw new IllegalStateException("Booking state is active, unable to remove booking with id: " + targetId);
        }

        /// Look at every booking and checks the booking ID specifically
        boolean removed = bookingsDao.removeIf(booking -> booking.getUserBookingID().equals(targetId));

        /// Booking removed
        if (removed)
            return;

        ///  Booking not found
        throw new BookingNotFoundException(targetId);

    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        if (bookingId == null){
            throw new IllegalArgumentException(String.format("bookingId: %s cannot be null",bookingId));
        }

        ///  Retrieves booking with a matching booking id, throws BookingNotFoundException if not found
        return bookingsDao.stream()
                .filter(booking -> booking.getUserBookingID().equals(bookingId))
                .findFirst()
                .orElseThrow( () -> new BookingNotFoundException(bookingId));
    }

    @Override
    public List<Booking> getBookings() {
        return new ArrayList<>(this.bookingsDao);
    }

}
