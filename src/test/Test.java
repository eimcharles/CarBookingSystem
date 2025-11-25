package test;

import booking.Booking;
import car.*;
import user.User;
import user.UserDAO;
import user.UserService;

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


        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        userService.getUsersById(UUID.fromString("fe5b09ca-1593-4813-8cf4-6566d4b0349c"));

    }
}