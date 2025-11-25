package app;

import booking.Booking;
import booking.BookingService;
import car.Car;
import user.User;
import user.UserService;

import java.util.Scanner;
import java.util.UUID;

import static utility.CLIFormatUtility.*;
import static utility.CLIFormatUtility.displayCarDetailsFormatted;

public class CarBookingCLI {

    public static void makeACarBooking(UserService userService, BookingService bookingService, Scanner scanner){

        displayAllAvailableCars(bookingService);
        ///  TODO implement logic
    }

    /**
     *      Guides the user to input a specific User ID,
     *      validating the input format as a UUID.
     *
     *       It then retrieves all cars currently booked
     *      by that validated user from the BookingService
     *      and prints them to the console.
     *
     *      If the user has no active bookings, a
     *      corresponding message is displayed.
     *
     *      @param userService
     *
     *      The UserService instance used to display
     *      available user IDs.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch
     *      the user's booked car data.
     *
     *      @param scanner
     *
     *      The Scanner instance used to handle
     *      user input for the User ID.
     */

    public static void displayUserBookedCars(UserService userService, BookingService bookingService, Scanner scanner){

        String userIdInput;
        UUID userId = null;
        // Set the user input to invalid first
        boolean isValidInput = false;

        // Display Feature Header and list all available users for the user to pick an ID from.
        displayAllUsers(userService);

        do {

            // Prompt user for input
            displayIndentedUserInput("➡️", "Please enter the User ID (e.g., 'U1001') to view user booked cars: ");
            userIdInput = scanner.nextLine().trim();

            // Check 1: Handle empty input
            if (userIdInput.isEmpty()) {
                displayIndentedMessage("⚠️", "User ID cannot be empty. Please try again.");

                // Skips the rest of the current loop body
                continue;
            }

            try {

                // Check 2: Attempt to convert the input string to a UUID object.
                userId = UUID.fromString(userIdInput);

                // If the conversion succeeds, set flag to true to exit loop
                isValidInput = true;

            } catch (IllegalArgumentException e){

                // If conversion fails, print error message and loop continues (isValidInput remains false)
                displayIndentedMessage("⚠️","Invalid User ID: "  + userIdInput);

                // List all available users for the user to pick an ID from again.
                displayAllUsers(userService);

            }

        } while (!isValidInput);

        Car[] bookedCars = bookingService.getUserBookedCarsByUserId(userId);

        if (bookedCars == null || bookedCars.length == 0){

            displayResultsMenu(TITLE_USER_BOOKED_CARS);
            displayIndentedMessage("❌", "User ID '" + userIdInput + "' has no active car bookings.");

        } else {

            displayResultsMenu(TITLE_USER_BOOKED_CARS);
            for (Car bookedCar : bookedCars) {
                displayCarDetailsFormatted(bookedCar);
            }
        }
    }

    /**
     *      Retrieves all currently available
     *      cars that run on gasoline from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the gas car is
     *      not tied to any active booking.
     *
     *       If no gas cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAvailableGasCars();

        displayResultsMenu(TITLE_GAS_CARS);
        if (availableGasCars == null || availableGasCars.length == 0) {
            displayIndentedMessage("❌", "No gas cars currently available for booking");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            displayCarDetailsFormatted(availableGasCar);
        }
    }

    /**
     *      Retrieves all currently available
     *      cars that run on electricity from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the electric car is
     *       not tied to any active booking.
     *
     *      If no electric cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        displayResultsMenu(TITLE_ELECTRIC_CARS);
        if (availableElectricCars == null || availableElectricCars.length == 0) {
            displayIndentedMessage("❌", "No electric cars currently available for booking");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            displayCarDetailsFormatted(availableEelectricCar);
        }
    }

    /**
     *      Retrieves all currently available cars from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the car is not tied to any active booking.
     *
     *      If no cars are available, a corresponding message
     *      is displayed using `displayIndentedMessage` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ALL_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the availability data.
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] availableCars = bookingService.getAllAvailableCars();

        displayResultsMenu(TITLE_ALL_CARS);
        if (availableCars == null || availableCars.length == 0) {
            displayIndentedMessage("❌", "No cars currently available for booking");
            return;
        }

        for (Car availableCar : availableCars) {
            displayCarDetailsFormatted(availableCar);
        }
    }

    /**
     *      Retrieves all active
     *      bookings from the BookingService
     *      and prints them to the console.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the data.
     *
     */

    public static void displayAllBooking(BookingService bookingService){

        Booking[] allBookings = bookingService.getAllBookings();

        displayResultsMenu(TITLE_ALL_BOOKINGS);
        if (allBookings == null || allBookings.length == 0){
            displayIndentedMessage("❌", "No bookings currently registered in the system");
            return;
        }

        for (Booking allBooking : allBookings) {
            System.out.println(allBooking);
        }
    }

    /**
     *        Retrieves all registered users from the
     *        UserService and prints them to the console
     *        using the standardized `displayUserDetailsFormatted` method.
     *
     *        If no users are found, a corresponding message
     *        is displayed using `displayIndentedMessage` method.
     *
     *        The results are presented under the menu title
     *        defined by `TITLE_REGISTERED_USERS`.
     *
     *        @param userService
     *
     *        The UserService instance used to fetch the user data.
     */

    public static void displayAllUsers(UserService userService){

        User[] users = userService.getAllUsers();

        displayResultsMenu(TITLE_REGISTERED_USERS);
        if (users == null || users.length == 0){
            displayIndentedMessage("❌", "No users currently registered in the system");
            return;
        }

        for (User user : users) {
            displayUserDetailsFormatted(user);
        }
    }
}
