package com.eimc.booking.dao;

import com.eimc.booking.Booking;
import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.exception.BookingNotFoundException;
import com.eimc.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *      ListBookingDAO is a Data Access Object (DAO)
 *      class for managing Car Booking objects,
 *      it implements BookingDAO contract.
 *
 *      This implementation stores and manages Car Bookings using arrays.
 */

public class ListBookingDAO implements BookingDAO {

    private final List<Booking> bookingsDao = new ArrayList<>(MAX_CAPACITY);

    /// Defines a maximum capacity for bookings in system.
    private static final int MAX_CAPACITY = 3;

    public ListBookingDAO() {

        Booking initialBooking =  new Booking(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

        /// Total Bookings in system: 1 (bookings slots available: 2)
        addBooking(initialBooking);

    }

    @Override
    public void addBooking(Booking carBooking) {

        ///  Check if null
        if (carBooking == null)
            throw new IllegalArgumentException("Booking cannot be null");

        ///  Check if there is space in bookings DA0
        if (bookingsDao.size() >= MAX_CAPACITY)
            throw new IllegalStateException(String.format("The system has no more available space to add bookings - total space available: %d", MAX_CAPACITY));

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
