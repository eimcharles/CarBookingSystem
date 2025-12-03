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
                        // To Make a Car Booking (By User ID and Car Registration Number)
                        makeACarBookingByUserIdAndRegistrationNumber(configuration.getUserService(), configuration.getBookingService(), scanner);
                        // Display All Available Cars
                        displayAllAvailableCars(configuration.getBookingService());
                        break;
                    case "2":
                        // To Cancel a Car Booking (By Booking ID)
                        ///  TO implement
                        break;
                    case "3":
                        // To Display All Available Cars
                        displayAllAvailableCars(configuration.getBookingService());
                        break;
                    case "4":
                        // To Display Available Gasoline Cars
                        displayAllAvailableGasCars(configuration.getBookingService());
                        break;
                    case "5":
                        // To Display Available Electric Cars
                        displayAllAvailableElectricCars(configuration.getBookingService());
                        break;
                    case "6":
                        // To Display All Active Bookings
                        displayAllActiveBookings(configuration.getBookingService());
                        break;
                    case "7":
                        // To Display Cars Booked by User (By User ID)
                        displayUserBookedCarsByUserId(configuration.getUserService(), configuration.getBookingService(), scanner);
                        break;
                    case "8":
                        // To Display All Registered Users
                        displayAllRegisteredUsers(configuration.getUserService());
                        break;
                    case "9":
                        // To View Main Menu
                        displayMainMenu();
                        break;
                    case "0":
                        // To Exit Application
                        displayFormattedMessage("👋","Thank you for using the Car Booking System. Goodbye!");
                        scanner.close();
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
