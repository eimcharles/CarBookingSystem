
/**
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import Booking.BookingDAO;
import Car.Car;
import Car.CarDAO;
import Car.CarService;
import User.User;
import User.UserDAO;
import User.UserService;

import java.util.Arrays;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        BookingDAO bookingDAO = new BookingDAO();

        // Empty array with no bookings
        System.out.println(Arrays.toString(BookingDAO.getBookingsDao()));

    }
}