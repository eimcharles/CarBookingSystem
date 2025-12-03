package app;

import configuration.Configuration;

import static app.CarBookingCLI.*;
import static app.CLIFormatUtility.*;

import java.util.Scanner;

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
        displayMainMenu();
        displaySystemGuidelines();

        // Application Loop (The Menu)
        while (true) {

            try {

                displayUserInput();
                userInput = scanner.nextLine().trim();

                switch (userInput) {
                    case "1":
                        // Display All Available Cars
                        displayAllAvailableCars(configuration.getBookingService());
                        break;
                    case "2":
                        // Display Available Gasoline Cars
                        displayAllAvailableGasCars(configuration.getBookingService());
                        break;
                    case "3":
                        // Display Available Electric Cars
                        displayAllAvailableElectricCars(configuration.getBookingService());
                        break;
                    case "4":
                        // Make a Car Booking (By User ID and Car Registration Number)
                        makeACarBooking(configuration.getUserService(), configuration.getBookingService(), scanner);
                        break;
                    case "5":
                        // Display Cars Booked by User (By User ID)
                        displayUserBookedCars(configuration.getUserService(), configuration.getBookingService(), scanner);
                        break;
                    case "6":
                        // Display All Active Bookings
                        displayAllBooking(configuration.getBookingService());
                        break;
                    case "7":
                        // Display All Registered Users
                        displayAllUsers(configuration.getUserService());
                        break;
                    case "8":
                        // Display Main Menu for user
                        displayMainMenu();
                        break;
                    case "9":
                        // Upon exit
                        displayFormattedMessage("👋","Thank you for using the Car Booking System. Goodbye!");
                        // Close resources
                        scanner.close();
                        // Exit
                        return;
                    default:
                        // For invalid user inputs
                        displayFormattedMessage("❌","Invalid option. Please enter a number between 1 and 8.");
                        break;
                }

            } catch (Exception e) {
                // For all uncaught exceptions
                displayFormattedMessage("🚨","Unexpected error occurred: " + e.getMessage());

            }
        }
    }

}
