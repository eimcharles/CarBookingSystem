package app;

import booking.Booking;
import booking.BookingService;
import car.Car;
import car.CarService;
import exception.CarNotFoundException;
import exception.CarUnavailableException;
import exception.UserNotFoundException;
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
     *       Retrieves and prints all cars currently booked by a specific user.
     *
     *      This method first lists all available users and then enters a mandatory
     *      `do-while` input loop to enforce that the user provides a valid,
     *      non-empty User ID in the correct UUID format.
     *
     *      If the provided User ID is valid but the user has no bookings,
     *      a clear message is displayed using `displayIndentedMessage` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_USER_BOOKED_CARS`.
     *
     *      @param userService
     *
     *      The UserService instance used to fetch and display the list of all users.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the booked car data for the user.
     *
     *      @param scanner
     *
     *      The system input handler used to capture the user's keystrokes for the User ID.
     */

    public static void displayUserBookedCars(UserService userService, BookingService bookingService, Scanner scanner){

        // Validates the User ID from the console.
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Array of booked cars for the validated user ID.
        Car[] bookedCars = bookingService.getUserBookedCarsByUserId(validatedUserId);

        // Format and display the results based on retrieved data
        formatAndDisplayUserBookedCars(bookedCars, validatedUserId.toString());
    }

    private static void formatAndDisplayUserBookedCars(Car[] bookedCars, String validatedUserId){

        // No bookings found for the given userId
        if (bookedCars == null || bookedCars.length == 0){

            displayResultsMenu(TITLE_USER_BOOKED_CARS);
            displayIndentedMessage("❌", "User ID '" + validatedUserId + "' has no active car bookings.");

        } else {

            // bookings are found, display the results
            displayResultsMenu(TITLE_USER_BOOKED_CARS);
            for (Car bookedCar : bookedCars) {
                displayCarDetailsFormatted(bookedCar);
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

        displayAllUsers(userService);
        displayUserIDInteractionMenu();

        do {

            displayIndentedUserInput("➡️", "Please enter the User ID: ");
            userIdInput = scanner.nextLine().trim();

            // Handles empty input
            if (userIdInput.isEmpty()) {
                displayIndentedMessage("⚠️", "User ID cannot be empty - Please try again.");

                // Empty input: Skip the rest of the loop
                continue;
            }

            try {

                // Validating Input: Convert the input string to a UUID object.
                userId = UUID.fromString(userIdInput);

                // Validating User Existence: throws if user doesn't exist
                userService.getUsersById(userId);

                // Input and user exist and are valid
                isValidInput = true;

            } catch (IllegalArgumentException e) {

                // Handles bad UUID format
                displayIndentedMessage("❌","Invalid User ID Format: "  + userIdInput);

            } catch (UserNotFoundException e) {

                // User doesn't exist
                displayIndentedMessage("❌",e.getMessage());

            } catch (Exception e) {

                // Catches all unforeseen system errors
                displayIndentedMessage("❌", "Displaying users booked cars failed - Please try again.");

            }

            // Re-display on invalid inputs
            if (!isValidInput){
                displayAllUsers(userService);
                displayUserIDInteractionMenu();
            }

        } while (!isValidInput);

        return userId;

    }

    /**
     *      Retrieves all currently available gas cars from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the gas car is not tied to any active booking.
     *
     *      If no gas cars are available, a corresponding message
     *      is displayed using `displayIndentedMessage` method.
     *
     *      The results are presented under the menu title
     *       defined by `TITLE_GAS_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
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
     *      Retrieves all currently available electric cars from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the electric car is not tied to any active booking.
     *
     *      If no electric cars are available, a corresponding message
     *      is displayed using `displayIndentedMessage` method.
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
     *      a corresponding message is displayed
     *      using `displayIndentedMessage` method.
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

        displayResultsMenu(TITLE_ALL_BOOKINGS);
        if (allBookings == null || allBookings.length == 0){
            displayIndentedMessage("❌", "No bookings currently registered in the system");
            return;
        }

        for (Booking allBooking : allBookings) {
            displayBookingDetailsFormatted(allBooking);
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
