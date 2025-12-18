package com.eimc.booking.dao;

import com.eimc.booking.Booking;
import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.exception.BookingNotFoundException;
import com.eimc.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 *      ArrayBookingDAO is a Data Access Object (DAO)
 *      class for managing Car Booking objects,
 *      it implements BookingDAO contract.
 *
 *      This implementation stores and manages Car Bookings using arrays.
 */

public class ArrayBookingDAO implements BookingDAO {

    private final Booking[] bookingsDao;

    // Tracks the index of the next available slot in bookingsDao
    private int numberOfBookings = 0;

    // Defines a maximum size of the array storage.
    private static final int MAX_CAPACITY = 3;

    public ArrayBookingDAO() {

        // Storage for available bookings
        this.bookingsDao = new Booking[MAX_CAPACITY];

        Booking initialBooking =  new Booking(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

        // Total Bookings in system: 1 (bookings slots available: 2)
        addBooking(initialBooking);

    }

    @Override
    public Booking[] getBookings() {
        // Copy of bookingsDao returned from BookingDAO
        return Arrays.copyOf(this.bookingsDao, this.bookingsDao.length);
    }

    /**
     *      addBooking() adds a new car booking to bookingsDao
     *      array.
     *
     *      The booking is stored at the next available
     *      index in the internal array, and the index
     *      is then incremented.
     *
     *      @throws IllegalStateException if the internal
     *      storage array is full and no more bookings can
     *      be added.
     */

    @Override
    public void addBooking(Booking carBooking) {

        // Checks if index of numberOfBookings has reached MAX_CAPACITY
        if (this.numberOfBookings >= this.bookingsDao.length) {
            throw new IllegalStateException(String.format("No space available to add bookings - total bookings space available:  %d", this.bookingsDao.length));
        }

        // Adds the carBooking into the index that numberOfBookings points to
        this.bookingsDao[this.numberOfBookings] = carBooking;

        // Moves the index to point to the following array index
        this.numberOfBookings++;
    }

    /**
     *      removeBooking() removes a booking from the bookingDAO
     *      array by setting the booking reference to null.
     */

    @Override
    public void removeBooking(Booking carBookingToUpdate) {

        UUID targetId = carBookingToUpdate.getUserBookingID();

        // Check the booking state
        if (carBookingToUpdate.isBookingActive()) {
            throw new IllegalStateException("Booking state is active, unable to remove booking with id: " + targetId);
        }

        for (int i = 0; i < this.numberOfBookings; i++) {

            Booking currentBooking = this.bookingsDao[i];

            // Match the current booking with the targetId
            if (currentBooking != null && currentBooking.getUserBookingID().equals(targetId)) {

                // Shift all subsequent elements one position to the left to fill removed booking
                for (int j = i; j < this.numberOfBookings - 1; j++) {
                    this.bookingsDao[j] = this.bookingsDao[j + 1];
                }

                    // Nullify the last position
                    bookingsDao[numberOfBookings - 1] = null;

                    // Decrement numberOfBookings
                    numberOfBookings--;

                    // Booking found and removed
                    return;
            }
        }

        // Booking not found
        throw new BookingNotFoundException(targetId);

    }

    /**
     *      getBookingById() finds a booking whose user
     *      id matches the provided UUID in the bookingDAO array.
     *.
     *      @throws BookingNotFoundException If no booking
     *      with the given id exists in bookingDAO array.
     */

    @Override
    public Booking getBookingById(UUID bookingId) {
        for (Booking booking: getBookings()){
            if (booking != null && booking.getUserBookingID().equals(bookingId)){
                return booking;
            }
        }

        // Booking not found
        throw new BookingNotFoundException(bookingId);

    }

}
