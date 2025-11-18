import Booking.Booking;
import Booking.BookingDAO;
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

        // Get the user from the user DAO
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        User testUser = userService.getUsersById(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"));

        // Get the car from the car DAO
        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);
        Car testCar = carService.getCarsById("123_1");
        System.out.println(Arrays.toString(carService.getElectricCars()));
        System.out.println(Arrays.toString(carService.getGasolineCars()));


        // Create a new booking Object with the test user and car
        Booking testBooking = new Booking(UUID.fromString("9d818235-ce3b-40e8-b74a-3674985c6bcd"), testUser, testCar, LocalDateTime.now());
        Booking testBooking2 = new Booking(UUID.fromString("9d818235-ce3b-40e8-b74a-3674985c6bcd"), testUser, testCar, LocalDateTime.now());


        BookingDAO bookingDAO = new BookingDAO();

        // Add the booking to the bookingDAO
        bookingDAO.addBooking(testBooking);
        bookingDAO.addBooking(testBooking);
        bookingDAO.addBooking(testBooking);


        // Display the whole list of bookings in the booking DAO
        System.out.println(Arrays.toString(BookingDAO.getBookingsDao()));

    }
}