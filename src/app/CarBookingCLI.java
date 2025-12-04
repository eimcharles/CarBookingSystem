package app;

import booking.BookingService;

import car.Car;
import user.User;
import user.UserService;

import exception.CarNotFoundException;
import exception.CarUnavailableException;

import java.util.Scanner;
import java.util.UUID;

import static app.CLIFormatUtility.*;
import static app.CLIInputUtility.*;
import static app.CLIDisplayUtility.*;

/**
 *      CarBookingCLI class is the central controller
 *      that takes user input,
 *      calls the business services,
 *      and orchestrates the display of results.
 *
 *      TODO write comment for all methods CarBookingCLI
 *      TODO finish implementation for CarBookingCLI Class
 *
 * */

public class CarBookingCLI {

    /**
     *      Handles the flow for making a new car booking.
     *
     *      1.Displays necessary menus and user instructions defined by `TITLE_MAKE_BOOKING`.
     *
     *      2. Guides the user to select, and validates a User ID for new car booking
     *      by using 'promptAndValidateUserID()' CLIDisplayUtility method.
     *
     *      2. Guides the user to select, and validates a car registration number for new car booking
     *      by using 'promptAndValidateCarRegistrationNumber()' CLIDisplayUtility method.
     *
     *      4. Once both identifiers are successfully validated, it delegates
     *      to the `processBookingTransaction()` helper method to make
     *      a new car booking.
     *
     *      @param userService
     *
     *      The UserService instance for user validation and lookup.
     *
     *      @param bookingService
     *
     *      The BookingService instance for car validation and booking creation.
     *
     *      @param scanner
     *
     *      The Scanner object used to capture console input from the user.
     */

    public static void makeACarBookingByUserIdAndRegistrationNumber(UserService userService, BookingService bookingService, Scanner scanner) {

        // Menus, instructions for entering userId to make booking
        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
        displayUserIdBookingGuidelines();
        displayAllRegisteredUsers(userService);

        // Validates the User ID from the console.
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Menus, instructions for entering registration number to make booking
        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
        displayRegistrationNumberBookingGuidelines();
        displayAllAvailableCars(bookingService);

        // Validates the Car registration number from the console.
        String validatedCarRegistration = promptAndValidateCarRegistrationNumber(bookingService, scanner);

        // Process booking once user ID and Car registration are validated
        processBookingTransaction(userService, bookingService, validatedUserId, validatedCarRegistration);

    }

    private static void processBookingTransaction(UserService userService, BookingService bookingService, UUID validatedUserId, String validatedCarRegistration){

        try {

            // Attempt to create user object using the validated UUID
            User user = userService.getUserById(validatedUserId);

            // if the user doesn't exist by userId
            if (user == null) {

                displayFormattedMessage("❌", "No user found with id " + validatedUserId.toString());
                return;

            }

            // Attempt to create the booking by associating the user and car registration
            UUID userBookingID = bookingService.addCarBooking(user, validatedCarRegistration);

            // Display booking success message
            displayResultsByMenuTitle(TITLE_BOOKING_SUCCESS_MENU);
            displayFormattedMessage("✅", "Successfully booked car with registration number " + validatedCarRegistration + " for " + user.getName() + " with booking id " + userBookingID);

        } catch (CarNotFoundException e) {

            // Car does not exist
            displayFormattedMessage("⚠️",e.getMessage());

        } catch (CarUnavailableException e) {

            // Car is unavailable
            displayFormattedMessage("⚠️", e.getMessage());

        } catch (Exception e) {

            displayFormattedMessage("❌", "Booking attempt failed - Please try again.");

        }

    }

    /**
     *      TODO add JavaDocs
     *
     *
     * */

    public static void cancelCarBookingByBookingId(UserService userService, BookingService bookingService, Scanner scanner) {

        ///  TODO to implement

    }

    /**
     *      Retrieves all cars that are associated to a user from
     *      the BookingService and prints them to the console using the
     *      standardized `formatAndDisplayUserBookedCars()` method.
     *
     *      This method guides the user to select, and validates a User ID
     *      using 'promptAndValidateUserID()' CLIDisplayUtility method.
     *
     *      Once the user input is validated it then
     *      retrieves the corresponding cars currently booked by that user.
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

    public static void displayUserBookedCarsByUserId(UserService userService, BookingService bookingService, Scanner scanner){

        // CARS BOOKED BY USER ID MENU
        displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);

        // Guidelines to Display Cars Booked by User (By User ID)
        displayCarsBookedByUserIdGuidelines();

        // REGISTERED USERS MENU
        displayAllRegisteredUsers(userService);

        // Validates the User ID from the console and returns a UUID object
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Array of user booked cars for the validated user ID.
        Car[] bookedCars = bookingService.getUserBookedCarsByUserId(validatedUserId);

        // Format and display the user booked cars
        formatAndDisplayUserBookedCars(bookedCars, validatedUserId.toString());
    }

}
