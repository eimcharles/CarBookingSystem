package test;

import booking.Booking;
import car.*;
import user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static utility.CLIFormatUtility.*;

/**
 *      Test.Test class for Car Booking System CLI
 * */

public class Test {

    public static void main(String[] args) {

//        Configuration configuration = new Configuration();
//        Scanner scanner = new Scanner(System.in);
//        CarBookingCLI.makeACarBooking(configuration.getUserService(), configuration.getBookingService(), scanner);

        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);

        carService.getCarByRegistrationNumber("123_9");

    }
}