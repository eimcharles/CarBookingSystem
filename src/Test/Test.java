package Test;

import Configuration.Configuration;
import App.CarBookingCLI;

import java.util.Scanner;

/**
 *      Test.Test class for Car Booking System CLI
 * */

public class Test {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        Scanner scanner = new Scanner(System.in);

        // Done
        CarBookingCLI.displayAllUsers(configuration.getUserService());

        // Done
        CarBookingCLI.displayAllAvailableCars(configuration.getBookingService());

        // Done
        CarBookingCLI.displayAllAvailableElectricCars(configuration.getBookingService());

        // Done
        CarBookingCLI.displayAllAvailableGasCars(configuration.getBookingService());

        // Done
        CarBookingCLI.displayAllBooking(configuration.getBookingService());

        // Done
//      CarBookingCLI.displayUserBookedCars(configuration.getUserService(), configuration.getBookingService(), scanner);

//        System.out.println("Make a car booking feature TO DO : ");
//        CarBookingCLI.makeACarBooking(configuration.getBookingService(), null, null);
//        System.out.println();

    }
}