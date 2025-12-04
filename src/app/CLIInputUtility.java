package app;

import booking.BookingService;
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
                userService.getUserById(userId);

                // Input and user exist and are valid.
                isValidInput = true;

            } catch (IllegalArgumentException e) {

                // Handles bad UUID format
                displayFormattedMessage("❌","Invalid User ID Format: "  + userIdInput);

            } catch (UserNotFoundException e) {

                // Handles User non-existence
                displayFormattedMessage("❌",e.getMessage());

            } catch (Exception e) {

                // Catches all unforeseen system errors
                displayFormattedMessage("❌", "Displaying users booked cars failed - Please try again.");

            }

            // Re-display on invalid inputs
            if (!isValidInput){
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
                bookingService.getUnbookedCarForBooking(userRegistrationInput);

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
}
