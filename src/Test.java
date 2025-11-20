import Booking.Booking;
import Booking.BookingDAO;
import Booking.BookingService;
import Car.Car;
import Car.CarService;
import Car.CarDAO;
import User.User;
import User.UserService;
import User.UserDAO;

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
        System.out.println(testUser.getUserId());
        System.out.println();

        // Adding a booking by user and car registration number
        String registrationNumber = "123_1";
        bookingService.addCarBooking(testUser, registrationNumber);

        // Get the list of user booked cars by user id
        Car[] userBookedCars = bookingService.getUserBookedCarsByUserId(UUID.fromString(userId));
        for (Car userBookedCar : userBookedCars) {
            System.out.println(userBookedCar);
        }
        System.out.println();

        // Getting all active bookings
        Booking[] booked = bookingService.getAllBookings();

        // Displaying bookings
        for (Booking booking : booked) {
            System.out.println(booking);
        }

        // All gas cars
        Car[] availableGasCars = bookingService.getAvailableGasCars();

        for (Car availableGasCar : availableGasCars) {
            System.out.println(availableGasCar);
        }
        System.out.println();

        // All electric cars
        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        for (Car availableElectricCar : availableElectricCars) {
            System.out.println(availableElectricCar);
        }
        System.out.println();

        // All cars
        Car[] allAvailableCars = bookingService.getAllAvailableCars();

        for (Car availableCar : allAvailableCars) {
            System.out.println(availableCar);
        }
        System.out.println();

    }
}