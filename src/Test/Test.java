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

        CarBookingCLI.displayAllAvailableCars(configuration.getBookingService());

//        System.out.println("Display all available electric cars feature: ");
//        CarBookingCLI.displayAllAvailableElectricCars(configuration.getBookingService());
//        System.out.println();

//        System.out.println("Display all available gas cars feature: ");
//        CarBookingCLI.displayAllAvailableGasCars(configuration.getBookingService());
//        System.out.println();

//        CarBookingCLI.displayUserBookedCars(configuration.getUserService(), configuration.getBookingService(), scanner);
//        System.out.println();

//        System.out.println("Display all active bookings feature (No current bookings): ");
//        CarBookingCLI.displayAllBooking(configuration.getBookingService());
//        System.out.println();

//        System.out.println("Make a car booking feature TO DO : ");
//        CarBookingCLI.makeACarBooking(configuration.getBookingService(), null, null);
//        System.out.println();

    }
}