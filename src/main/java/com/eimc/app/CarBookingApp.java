package com.eimc.app;

import com.eimc.configuration.Configuration;

import static com.eimc.app.CLIDisplayUtility.*;
import static com.eimc.app.CarBookingCLI.*;
import static com.eimc.app.CLIFormatUtility.*;

import java.util.Scanner;

/**
 *      CarBookingApp is the entry point to start the application.
 * */

public class CarBookingApp {

    public static void main(String[] args) {

        // Initialize System
        Configuration configuration = new Configuration();
        Scanner scanner = new Scanner(System.in);

        // CarBookingApplication
        runCarBookingApp(scanner, configuration);

    }

    private static void runCarBookingApp(Scanner scanner, Configuration configuration) {

        String userInput;

        // Display greeting, main menu and explain system interactions
        displayCarBookingGreeting();
        displaySystemGuidelines();
        displayMainMenu();

        // Application Loop (The Menu)
        while (true) {

            try {

                displayUserInput();
                userInput = scanner.nextLine().trim();

                switch (userInput) {
                    case "1":
                        // To Make a Car Booking (By User ID and Car Registration Number)
                        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
                        makeACarBookingByUserIdAndRegistrationNumber(configuration.getUserService(), configuration.getBookingService(), scanner);
                        break;
                    case "2":
                        // To Cancel a Car Booking (By Booking ID)
                        displayResultsByMenuTitle(TITLE_CANCEL_BOOKING);
                        cancelCarBookingByBookingId(configuration.getBookingService(), scanner);
                        break;
                    case "3":
                        // To Display All Available Cars
                        displayResultsByMenuTitle(TITLE_ALL_CARS);
                        displayAllAvailableCars(configuration.getBookingService());
                        break;
                    case "4":
                        // To Display Available Gasoline Cars
                        displayResultsByMenuTitle(TITLE_GAS_CARS);
                        displayAllAvailableGasCars(configuration.getBookingService());
                        break;
                    case "5":
                        // To Display Available Electric Cars
                        displayResultsByMenuTitle(TITLE_ELECTRIC_CARS);
                        displayAllAvailableElectricCars(configuration.getBookingService());
                        break;
                    case "6":
                        // To Display All Active Bookings
                        displayResultsByMenuTitle(TITLE_ACTIVE_BOOKINGS);
                        displayAllActiveBookings(configuration.getBookingService());
                        break;
                    case "7":
                        // To Display Cars Booked by User (By User ID)
                        displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
                        displayCarsBookedByUserByUserId(configuration.getUserService(), configuration.getBookingService(), scanner);
                        break;
                    case "8":
                        // To Display All Registered Users
                        displayResultsByMenuTitle(TITLE_REGISTERED_USERS);
                        displayAllRegisteredUsers(configuration.getUserService());
                        break;
                    case "9":
                        // To View Main Menu
                        displayMainMenu();
                        break;
                    case "0":
                        // To Exit Application
                        displayFormattedMessage("👋","Thank you for using the Car Booking System - Goodbye!");
                        scanner.close();
                        return;
                    default:
                        // For invalid user inputs
                        displayFormattedMessage("❌","Invalid option - please enter a number between 1 to 9 or 0 to EXIT.");
                        break;
                }

            } catch (Exception e) {
                // For all uncaught exceptions
                displayFormattedMessage("🚨","Unexpected error occurred: " + e.getMessage());

            }
        }
    }

}
