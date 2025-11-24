package Test;

import Configuration.Configuration;
import App.CarBookingCLI;

/**
 *      Test.Test class for Car Booking System CLI
 * */

public class Test {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        System.out.println("Display all users feature");
        CarBookingCLI.displayAllUsers(configuration.getUserService());
        System.out.println();

        System.out.println("Display all available cars feature: ");
        CarBookingCLI.displayAllAvailableCars(configuration.getBookingService());
        System.out.println();

        System.out.println("Display all available electric cars feature: ");
        CarBookingCLI.displayAllAvailableElectricCars(configuration.getBookingService());
        System.out.println();

        System.out.println("Display all available gas cars feature: ");
        CarBookingCLI.displayAllAvailableGasCars(configuration.getBookingService());
        System.out.println();

        System.out.println("Display user booked cars gas cars feature TO DO : ");
        CarBookingCLI.displayUserBookedCars(configuration.getUserService());
        System.out.println();

        System.out.println("Display all active bookings feature (No current bookings): ");
        CarBookingCLI.displayAllBooking(configuration.getBookingService());
        System.out.println();

        System.out.println("Make a car booking feature TO DO : ");
        CarBookingCLI.makeACarBooking(configuration.getBookingService());
        System.out.println();

    }
}