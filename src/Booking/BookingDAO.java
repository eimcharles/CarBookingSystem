package Booking;

import java.util.Arrays;

/**
 *      Data Access Object (DAO) class for managing Car Booking objects.
 *      This implementation stores and manages Car Bookings using arrays.
 *
 *      TODO: Implement remaining Booking DAO functionality
 */

public class BookingDAO {

    private static final Booking[] bookingsDao;

    // Tracks the index of the next available slot in bookingsDao
    private static int nextAvailableIndex = 0;

    // Defines the fixed, maximum size of the array storage.
    private static final int MAX_CAPACITY = 10;

    static {

        // Storage for available bookings
        bookingsDao = new Booking[MAX_CAPACITY];

    }

    public static void addBooking(Booking carBooking) {

        // Checks if index of next available slot has reached length of array
        if (nextAvailableIndex >= bookingsDao.length) {
            throw new IllegalStateException(String.format("No more bookings available, total slots available %d", bookingsDao.length));
        }

        // Adds the carBooking into the slot that nextAvailableIndex points to
        bookingsDao[nextAvailableIndex] = carBooking;

        // Moves the index to point to the following slot
        nextAvailableIndex++;

    }

    public static Booking[] getBookingDao() {
        // Copy of bookingsDao returned from BookingDAO
        return Arrays.copyOf(bookingsDao, bookingsDao.length);
    }

}
