package test;

import app.CarBookingCLI;
import booking.Booking;
import booking.BookingDAO;
import booking.BookingService;
import car.*;
import configuration.Configuration;
import user.User;
import user.UserDAO;
import user.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

import static utility.CLIFormatUtility.*;

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

        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);

        BookingDAO bookingDAO = new BookingDAO();
        BookingService bookingService = new BookingService(bookingDAO, carService);
        CarBookingCLI.promptAndValidateCarRegistration(bookingService, scanner);

    }
}