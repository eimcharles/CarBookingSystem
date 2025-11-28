package booking;

import java.util.Arrays;

/**
 *      Data Access Object (DAO) class for managing Car Booking objects.
 *      This implementation stores and manages Car Bookings using arrays.
 */

public class BookingDAO {

    private final Booking[] bookingsDao;

    // Tracks the index of the next available slot in bookingsDao
    private int nextAvailableIndex = 0;

    // Defines the fixed, maximum size of the array storage.
    private static final int MAX_CAPACITY = 10;

    public BookingDAO() {

        // Storage for available bookings
        this.bookingsDao = new Booking[MAX_CAPACITY];

    }

    public  void addBooking(Booking carBooking) {

        // Checks if index of next available slot has reached length of array
        if (this.nextAvailableIndex >= this.bookingsDao.length) {
            throw new IllegalStateException(String.format("No more bookings available, total slots available %d", this.bookingsDao.length));
        }

        // Adds the carBooking into the slot that nextAvailableIndex points to
        this.bookingsDao[this.nextAvailableIndex] = carBooking;

        // Moves the index to point to the following slot
        this.nextAvailableIndex++;

    }

    public Booking[] getBookingDao() {
        // Copy of bookingsDao returned from BookingDAO
        return Arrays.copyOf(this.bookingsDao, this.bookingsDao.length);
    }

}
