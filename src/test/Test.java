package test;

import booking.Booking;
import car.Brand;
import car.Car;
import car.FuelType;
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

        displayResultsMenu(TITLE_ALL_BOOKINGS);

        displayBookingDetailsFormatted(
                new Booking(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer"),
                        new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC),
                        LocalDateTime.now()
                ));

//        CarBookingCLI.makeACarBooking(configuration.getUserService(), configuration.getBookingService(), scanner);

    }
}