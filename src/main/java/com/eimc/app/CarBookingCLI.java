package com.eimc.app;

import com.eimc.booking.BookingService;

import com.eimc.car.Car;
import com.eimc.exception.*;
import com.eimc.user.User;
import com.eimc.user.UserService;

import java.util.Scanner;
import java.util.UUID;

import static com.eimc.app.CLIFormatUtility.*;
import static com.eimc.app.CLIInputUtility.*;
import static com.eimc.app.CLIDisplayUtility.*;

/**
 *      CarBookingCLI class is the central controller.
 *
 *      It calls the business services, and orchestrates:
 *
 *      - The creation of a Car booking.
 *      - The cancellation of a Car booking
 *      - Displaying all cars that are booked by a given user.
 * */

public class CarBookingCLI {

    /**
     *      makeACarBookingByUserIdAndRegistrationNumber() handles the
     *      flow for making a new car booking by user id and registration number.
     *
     *      It validates available cars for booking.
     *
     *      - Guides the user to select, and validates a User ID for new car booking.
     *
     *      - Guides the user to select, and validates a car registration number for new car booking.
     *
     *      - Once both identifiers are successfully validated, creates a new car booking.
     */

    public static void makeACarBookingByUserIdAndRegistrationNumber(UserService userService, BookingService bookingService, Scanner scanner) {

        // if no available car exist - return
        if (!bookingService.hasAvailableCarsForBooking()){
            displayFormattedMessage("❌", "The system has no cars currently available for booking.");
            return;
        }

        // Menus, instructions for entering userId to make booking
        displayAllRegisteredUsers(userService);
        displayUserIdBookingGuidelines();

        // Validates the user id from the console and returns a validated UUID object
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Menus, instructions for entering registration number to make booking
        displayResultsByMenuTitle(TITLE_MAKE_BOOKING);
        displayAllAvailableCars(bookingService);
        displayRegistrationNumberBookingGuidelines();

        // Validates the car registration number from the console.
        String validatedCarRegistration = promptAndValidateCarRegistrationNumber(bookingService, scanner);

        // Create booking once user id and Car registration are validated
        processBookingTransaction(userService, bookingService, validatedUserId, validatedCarRegistration);

    }

    private static void processBookingTransaction(UserService userService, BookingService bookingService, UUID validatedUserId, String validatedCarRegistration){

        try {

            // Attempt to create user object using the validated UUID
            User user = userService.getUserById(validatedUserId);

            // Attempt to create the booking by associating the user and car registration
            UUID userBookingID = bookingService.addCarBookingByUserAndRegistrationNumber(user, validatedCarRegistration);

            // Display booking success message
            displayResultsByMenuTitle(TITLE_BOOKING_SUCCESS_MENU);
            displayFormattedMessage("✅", "Successfully created a car booking for " +
                    user.getFirstName() + " with booking id: " + userBookingID + ".");

        }  catch (UserNotFoundException e) {

            // User does not found
            displayFormattedMessage("⚠️",e.getMessage());

        } catch (CarNotFoundException e) {

            // Car does not found
            displayFormattedMessage("⚠️",e.getMessage());

        } catch (CarUnavailableException e) {

            // Car is unavailable (booked)
            displayFormattedMessage("⚠️", e.getMessage());

        } catch (IllegalStateException e) {

            // No more slot available for bookings
            displayFormattedMessage("⚠️", e.getMessage());

        } catch (Exception e) {

            displayFormattedMessage("❌", "Booking attempt failed - Please try again.");

        }

    }

    /**
     *      cancelCarBookingByBookingId() handles the cancellation of an active car booking by booking id.
     *
     *      It validates active bookings in the system:
     *
     *      - Guides the user to select, and validates a Booking ID for cancellation
     *
     *      - Once the booking ID is successfully validated, it cancels the car booking and releases the associated car.
     */

    public static void cancelCarBookingByBookingId(BookingService bookingService, Scanner scanner) {

        // if no active bookings exist - return
        if (!bookingService.hasActiveBookings()){
            displayFormattedMessage("❌", "The system has no active bookings currently registered.");
            return;
        }

        displayAllActiveBookings(bookingService);
        displayCancelBookingByBookingIdGuidelines();

        // Validates the booking id from the console and returns a validated UUID object.
        UUID validatedBookingId = promptAndValidateBookingID(bookingService, scanner);

        //  Cancel car booking once Booking id is validated
        processBookingCancellation(bookingService, validatedBookingId);

    }

    private static void processBookingCancellation(BookingService bookingService, UUID validatedBookingId) {

            try {

                // Attempt to cancel the booking
                bookingService.cancelActiveBookingByBookingId(validatedBookingId);
                displayResultsByMenuTitle(TITLE_CANCELLATION_SUCCESS_MENU);
                displayFormattedMessage("✅", "Successfully cancelled the booking with booking id: " + validatedBookingId + ".");

            } catch (BookingNotFoundException e) {

                // Booking not found
                displayFormattedMessage("❌", e.getMessage());

            }  catch (Exception e) {

                displayFormattedMessage("❌", "No bookings currently registered in the system");

            }

    }

    /**
     *      displayCarsBookedByUserByUserId() retrieves all car booked by a user by user id.
     *
     *      - Guides the user to select, and validates a User ID
     *
     *      - Once the user input is validated, it retrieves the corresponding cars booked by that user.
     */

    public static void displayCarsBookedByUserByUserId(UserService userService, BookingService bookingService, Scanner scanner){

        displayAllRegisteredUsers(userService);
        displayCarsBookedByUserIdGuidelines();

        // Validates the user id from the console and returns a validated UUID object
        UUID validatedUserId = promptAndValidateUserID(userService, scanner);

        // Array of user booked cars for the validated user id.
        Car[] bookedCars = bookingService.getAllBookedCarsByUserId(validatedUserId);

        // Format and display the user booked cars
        displayUserBookedCars(bookedCars, validatedUserId.toString());
    }

}
