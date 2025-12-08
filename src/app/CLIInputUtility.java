package app;

import booking.BookingService;
import exception.BookingNotFoundException;
import user.UserService;

import exception.CarNotFoundException;
import exception.CarUnavailableException;
import exception.UserNotFoundException;

import java.util.Scanner;
import java.util.UUID;

import static app.CLIDisplayUtility.*;
import static app.CLIFormatUtility.*;

/**
 *
 *      CLIInputUtility is a static class responsible for
 *      prompting the user and validating their
 *      input (e.g., validating a User ID or Booking ID).
 *
 * */

public class CLIInputUtility {

    public static UUID promptAndValidateUserID(UserService userService, Scanner scanner){

        // User Input '8ca51d2b-aaaf-4bf2-834a-e02964e10fc3'
        String userIdInput;

        // Holds the validated and converted userId object
        UUID userId = null;

        // Flag for input validation
        boolean isValidInput = false;

        do {

            displayFormattedUserInput("➡️", "Please enter the USER ID: ");
            userIdInput = scanner.nextLine().trim();

            // Handles empty input
            if (userIdInput.isEmpty()) {
                displayFormattedMessage("⚠️", "USER ID cannot be empty - Please try again.");

                // Empty input: Skip the rest of the loop
                continue;
            }

            try {

                // Validates Input: Convert the input string to a UUID object.
                userId = UUID.fromString(userIdInput);

                // Validates User Existence: throws if user doesn't exist.
                userService.getUserById(userId);

                // Input and user exist and are valid.
                isValidInput = true;

            } catch (IllegalArgumentException e) {

                // Handles bad UUID format
                displayFormattedMessage("❌","Invalid USER ID Format: "  + userIdInput);

            } catch (UserNotFoundException e) {

                // Handles User non-existence
                displayFormattedMessage("❌",e.getMessage());

            } catch (Exception e) {

                // Catches all unforeseen system errors
                displayFormattedMessage("❌", "Displaying users booked cars failed - Please try again.");

            }

            // Re-display on invalid inputs
            if (!isValidInput){
                displayResultsByMenuTitle(TITLE_REGISTERED_USERS);
                displayAllRegisteredUsers(userService);
                displayUserIdBookingGuidelines();
            }

        } while (!isValidInput);

        return userId;

    }

    public static String promptAndValidateCarRegistrationNumber(BookingService bookingService, Scanner scanner){

        // User input "123_1"
        String userRegistrationInput;

        // Flag for input validation
        boolean isValidInput = false;

        do {

            displayFormattedUserInput("➡️", "Please enter the CAR REGISTRATION Number: ");
            userRegistrationInput = scanner.nextLine().trim();

            // Handles empty input
            if (userRegistrationInput.isEmpty()) {
                displayFormattedMessage("⚠️", "CAR REGISTRATION Number cannot be empty - Please try again.");

                // Empty input: Skip the rest of the loop
                continue;

            }

            try {

                // Validates available car: existence and car current availability
                bookingService.getAvailableCarForBookingByRegistrationNumber(userRegistrationInput);

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
                displayResultsByMenuTitle(TITLE_ALL_CARS);
                displayAllAvailableCars(bookingService);
                displayRegistrationNumberBookingGuidelines();
            }

        } while (!isValidInput);

        return userRegistrationInput;

    }

    public static UUID promptAndValidateBookingID(BookingService bookingService, Scanner scanner){

        // User Input '8ca51d2b-aaaf-4bf2-834a-e02964e10fc3'
        String bookingIdInput;

        // Holds the validated and converted bookingId object
        UUID bookingId = null;

        // Flag for input validation
        boolean isValidInput = false;

            do {

                displayFormattedUserInput("➡️", "Please enter the BOOKING ID: ");
                bookingIdInput = scanner.nextLine().trim();

                // Handles empty input
                if (bookingIdInput.isEmpty()) {
                    displayFormattedMessage("⚠️", "BOOKING ID cannot be empty - Please try again.");

                    // Empty input: Skip the rest of the loop
                    continue;
                }

                try {

                    // Validates Input: Convert the input string to a UUID object.
                    bookingId = UUID.fromString(bookingIdInput);

                    // Validates Booking Existence: throws if booking doesn't exist.
                    bookingService.getBookingByBookingId(bookingId);

                    // Input and booking exist and are valid.
                    isValidInput = true;

                } catch (IllegalArgumentException e){

                    // Handles bad UUID format
                    displayFormattedMessage("❌", "Invalid BOOKING ID Format: " + bookingIdInput);

                } catch (BookingNotFoundException e) {

                    // Handles Booking non-existence
                    displayFormattedMessage("❌", e.getMessage());

                } catch (Exception e) {

                    displayFormattedMessage("❌", "Invalid BOOKING ID");

                }


                // Re-display on invalid inputs
                if (!isValidInput){
                    displayResultsByMenuTitle(TITLE_ACTIVE_BOOKINGS);
                    displayAllActiveBookings(bookingService);
                    displayCancelBookingByBookingIdGuidelines();
                }

            } while (!isValidInput);

        return bookingId;
    }
}
