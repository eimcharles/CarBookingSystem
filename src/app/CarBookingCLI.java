package app;

import booking.Booking;
import booking.BookingService;
import car.Car;
import exception.CarNotFoundException;
import exception.CarUnavailableException;
import exception.UserNotFoundException;
import user.User;
import user.UserService;

import java.util.Scanner;
import java.util.UUID;

import static utility.CLIFormatUtility.*;
import static utility.CLIFormatUtility.displayFormattedCarDetails;

/**
 *      TODO write comment for CarBookingCLI
 *      TODO write comment for all methods CarBookingCLI
 *      TODO finish implementation
 * */

public class CarBookingCLI {

    public static void makeACarBooking(UserService userService, BookingService bookingService, Scanner scanner) {

        // Menus, instructions for entering userId to make booking
        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
        displayUserIdBookingGuidelines();
        displayAllUsers(userService);

        // Validates the User ID from the console.
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Menus, instructions for entering registration number to make booking
        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
        displayRegistrationNumberBookingGuidelines();
        displayAllAvailableCars(bookingService);

        // Validates the Car registration number from the console.
        String validatedCarRegistration = promptAndValidateCarRegistrationNumber(bookingService, scanner);

    }

    private static String promptAndValidateCarRegistrationNumber(BookingService bookingService, Scanner scanner){

        // User input "123_1"
        String userRegistrationInput;

        // Flag for input validation
        boolean isValidInput = false;

        do {

            displayFormattedUserInput("➡️", "Please enter the Car Registration Number: ");
            userRegistrationInput = scanner.nextLine().trim();

            // Handles empty input
            if (userRegistrationInput.isEmpty()) {
                displayFormattedMessage("⚠️", "Car Registration Number cannot be empty - Please try again.");

                // Empty input: Skip the rest of the loop
                continue;

            }

            try {

                // Validates available car: existence and car current availability
                bookingService.getAvailableCarForBooking(userRegistrationInput);

                // Car exists and is currently available
                isValidInput = true;

            } catch (CarNotFoundException e) {

                // Car does not exist
                displayFormattedMessage("⚠️", e.getMessage());

            } catch (CarUnavailableException e) {

                // Car is unavailable
                displayFormattedMessage("⚠️", e.getMessage());

            } catch (Exception e) {

                // Catches all unforeseen system errors
                displayFormattedMessage("❌", "Booking attempt failed - Please try again.");

            }

            // Re-display on invalid inputs
            if (!isValidInput){
                displayAllAvailableCars(bookingService);
                displayRegistrationNumberBookingGuidelines();
            }

        } while (!isValidInput);

        return userRegistrationInput;

    }

    /**
     *      Retrieves all cars that are associated to a user from
     *      the BookingService and prints them to the console using the
     *      standardized `formatAndDisplayUserBookedCars()` method.
     *
     *      This method guides the user to select and validates a User ID
     *      using 'promptAndValidateUserID()' method, once the user input is
     *      validated it then retrieves the corresponding cars currently booked by that user.
     *
     *      If no cars are actively booked by the user, a corresponding message
     *      is displayed using the `displayFormattedMessage()` method within the helper.
     *
     *      The results are presented in formatAndDisplayUserBookedCars()
     *      defined by `TITLE_USER_BOOKED_CARS`.
     *
     *      @param userService
     *      The UserService instance used to display all registered users and validate the entered ID.
     *
     *      @param bookingService
     *      The BookingService instance used to fetch the booked cars associated with the user.
     *
     *      @param scanner
     *      The Scanner object used for reading the user's input (the User ID).
     */

    public static void displayUserBookedCars(UserService userService, BookingService bookingService, Scanner scanner){

        // CARS BOOKED BY USER ID MENU
        displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);

        // Guidelines to Display Cars Booked by User (By User ID)
        displayCarsBookedByUserIdGuidelines();

        // REGISTERED USERS MENU
        displayAllUsers(userService);

        // Validates the User ID from the console and returns a UUID object
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Array of user booked cars for the validated user ID.
        Car[] bookedCars = bookingService.getUserBookedCarsByUserId(validatedUserId);

        // Format and display the user booked cars
        formatAndDisplayUserBookedCars(bookedCars, validatedUserId.toString());
    }

    private static void formatAndDisplayUserBookedCars(Car[] bookedCars, String validatedUserId){

        if (bookedCars == null || bookedCars.length == 0){

            // No bookings for the given user id
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            displayFormattedMessage("❌", "User ID '" + validatedUserId + "' has no active car bookings.");

        } else {

            // Display and format user booked car results
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            for (Car bookedCar : bookedCars) {
                displayFormattedCarDetails(bookedCar);
            }
        }
    }

    private static UUID promptAndValidateUserID(UserService userService, Scanner scanner){

        // User Input '8ca51d2b-aaaf-4bf2-834a-e02964e10fc3'
        String userIdInput;

        // Holds the validated and converted userId object
        UUID userId = null;

        // Flag for input validation
        boolean isValidInput = false;

        do {

            displayFormattedUserInput("➡️", "Please enter the User ID: ");
            userIdInput = scanner.nextLine().trim();

            // Handles empty input
            if (userIdInput.isEmpty()) {
                displayFormattedMessage("⚠️", "User ID cannot be empty - Please try again.");

                // Empty input: Skip the rest of the loop
                continue;
            }

            try {

                // Validates Input: Convert the input string to a UUID object.
                userId = UUID.fromString(userIdInput);

                // Validates User Existence: throws if user doesn't exist.
                userService.getUsersById(userId);

                // Input and user exist and are valid.
                isValidInput = true;

            } catch (IllegalArgumentException e) {

                // Handles bad UUID format
                displayFormattedMessage("❌","Invalid User ID Format: "  + userIdInput);

            } catch (UserNotFoundException e) {

                // Handles User non existence
                displayFormattedMessage("❌",e.getMessage());

            } catch (Exception e) {

                // Catches all unforeseen system errors
                displayFormattedMessage("❌", "Displaying users booked cars failed - Please try again.");

            }

            // Re-display on invalid inputs
            if (!isValidInput){
                displayAllUsers(userService);
                displayCarsBookedByUserIdGuidelines();
            }

        } while (!isValidInput);

        return userId;

    }

    /**
     *      Retrieves all currently available gas cars from the
     *      BookingService and prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the gas car is not tied to any active booking.
     *
     *      If no gas cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_GAS_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAvailableGasCars();

        displayResultsByMenuTitle(TITLE_GAS_CARS);
        if (availableGasCars == null || availableGasCars.length == 0) {
            displayFormattedMessage("❌", "No gas cars currently available for booking");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            displayFormattedCarDetails(availableGasCar);
        }
    }

    /**
     *      Retrieves all currently available
     *      electric cars from the BookingService
     *      and prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the electric car is not tied to any active booking.
     *
     *      If no electric cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ELECTRIC_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        displayResultsByMenuTitle(TITLE_ELECTRIC_CARS);
        if (availableElectricCars == null || availableElectricCars.length == 0) {
            displayFormattedMessage("❌", "No electric cars currently available for booking");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            displayFormattedCarDetails(availableEelectricCar);
        }
    }

    /**
     *      Retrieves all currently available
     *      cars from the BookingService and
     *      prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the car is not tied to any active booking.
     *
     *      If no cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
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

        displayResultsByMenuTitle(TITLE_ALL_CARS);
        if (availableCars == null || availableCars.length == 0) {
            displayFormattedMessage("❌", "No cars currently available for booking");
            return;
        }

        for (Car availableCar : availableCars) {
            displayFormattedCarDetails(availableCar);
        }
    }

    /**
     *      Retrieves all active
     *      bookings from the BookingService
     *      and prints them to the console using the
     *      standardized `displayFormattedBookingDetails()` method.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed
     *      using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ALL_BOOKINGS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the data.
     *
     */

    public static void displayAllBooking(BookingService bookingService){

        Booking[] allBookings = bookingService.getAllBookings();

        displayResultsByMenuTitle(TITLE_ALL_BOOKINGS);
        if (allBookings == null || allBookings.length == 0){
            displayFormattedMessage("❌", "No bookings currently registered in the system");
            return;
        }

        for (Booking allBooking : allBookings) {
            displayFormattedBookingDetails(allBooking);
        }
    }

    /**
     *        Retrieves all registered users from the
     *        UserService and prints them to the console
     *        using the standardized `displayFormattedUserDetails()` method.
     *
     *        If no users are found, a corresponding message
     *        is displayed using `displayFormattedMessage()` method.
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

        displayResultsByMenuTitle(TITLE_REGISTERED_USERS);
        if (users == null || users.length == 0){
            displayFormattedMessage("❌", "No users currently registered in the system");
            return;
        }

        for (User user : users) {
            displayFormattedUserDetails(user);
        }
    }
}
