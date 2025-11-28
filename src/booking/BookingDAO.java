package booking;

/**
 *      BookingDAO Defines the contract for all data
 *      access operations related to Booking entities
 * */

public interface BookingDAO {

    void addBooking(Booking carBooking);

    Booking[] getBookingDao();
}
