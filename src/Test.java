import Booking.Booking;
import Booking.BookingDAO;
import Booking.BookingService;
import Car.Car;
import Car.CarService;
import Car.CarDAO;
import User.User;
import User.UserService;
import User.UserDAO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 *      Test class for Car Booking System CLI
 * */

public class Test {

    public static void main(String[] args) {

        // Dependencies
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        // Dependencies
        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);
        // Dependencies
        BookingDAO bookingDAO = new BookingDAO();
        BookingService bookingService = new BookingService(bookingDAO,carService);

        // Test user to create a new car booking with booking UUID
        String userId = "8ca51d2b-aaaf-4bf2-834a-e02964e10fc3";
        User testUser = userService.getUsersById(UUID.fromString(userId));
        System.out.println(testUser);

        // Adding a booking by user and car registration number
        String registrationNumber = "123_1";
        bookingService.addCarBooking(testUser, registrationNumber);

        // Getting all active bookings
        Booking[] booked = bookingService.getAllBookings();

        // Displaying bookings
        for (Booking booking : booked) {
            System.out.println(booking);
        }

        // Getting all cars that are associated to a userID
        Car[] userBookedCars = bookingService.getUserBookedCarsByUserId(UUID.fromString(userId));

        // Displaying all cars that are associated to a userID
        for (Car userBookedCar : userBookedCars) {
            System.out.println(userBookedCar);
        }

    }
}