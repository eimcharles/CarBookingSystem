package test;

import app.CarBookingCLI;
import configuration.Configuration;

import booking.BookingService;
import booking.dao.ArrayBookingDAO;

import car.CarService;
import car.dao.ArrayCarDAO;
import user.UserService;
import user.dao.ArrayUserDAO;

import java.util.Scanner;
import java.util.UUID;

/**
 *      Test class for Car Booking System CLI
 *
 *      Serves as a Testing environment to
 *      ensure proper implementation and
 *      output.
 * */

public class Test {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        Scanner scanner = new Scanner(System.in);

        ArrayCarDAO arrayCarDAO = new ArrayCarDAO();
        CarService carService = new CarService(arrayCarDAO);

        ArrayUserDAO arrayUserDAO = new ArrayUserDAO();
        UserService userService = new UserService(arrayUserDAO);

        ArrayBookingDAO arrayBookingDAO = new ArrayBookingDAO();
        BookingService bookingService = new BookingService(arrayBookingDAO, carService);

        // User not found
        CarBookingCLI.processBookingTransaction(userService, bookingService, UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc1"), "123_1");

        // Car not found
        CarBookingCLI.processBookingTransaction(userService, bookingService, UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "123_0");

        // Both user and car are validated process booking
        CarBookingCLI.processBookingTransaction(userService, bookingService, UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "123_1");

    }
}