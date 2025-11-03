package Booking;

/**
 *      Data Access Object (DAO) class for managing Car Booking objects.
 *      This implementation stores and manages Car Bookings using arrays.
 *
 *      TODO: Implement remaining Booking DAO functionality
 */

public class BookingDAO {

    private static final Booking[] bookingsDao;

    static {

        // Maximum bookings available
        bookingsDao = new Booking[10];

    }

    public static Booking[] getBookingsDao() {
        return bookingsDao;
    }
}
