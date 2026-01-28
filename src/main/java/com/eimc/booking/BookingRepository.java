package com.eimc.booking;

import com.eimc.exception.BookingNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingRepository {

    private final List<Booking> bookingsDao = new ArrayList<>();

    public BookingRepository() {}

    public void addBooking(Booking carBooking) {

        ///  Check if null
        if (carBooking == null)
            throw new IllegalArgumentException("Booking cannot be null");

        ///  Add booking
        bookingsDao.add(carBooking);
    }

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

    public List<Booking> getBookings() {
        return new ArrayList<>(this.bookingsDao);
    }

}
