package com.eimc.app;

import com.eimc.booking.Booking;
import com.eimc.user.User;
import com.eimc.car.Car;

import java.time.format.DateTimeFormatter;

/**
 *      CLIFormatUtility is a utility class that is part of the CarBookingApplication presentation layer.
 *
 *      It is responsible for formatting and displaying all output within the
 *      CarBookingCLI class.
 */

public class CLIFormatUtility {

    private static final int CLI_WIDTH = 230;
    private static final int TAB_COUNT = 18;

    public static final String TITLE_ALL_CARS = "ALL AVAILABLE CARS";
    public static final String TITLE_GAS_CARS = "ALL AVAILABLE GAS CARS";
    public static final String TITLE_ELECTRIC_CARS = "ALL AVAILABLE ELECTRIC CARS";

    public static final String TITLE_MAKE_BOOKING = "MAKE A CAR BOOKING BY USER ID AND REGISTRATION NUMBER";
    public static final String TITLE_CANCEL_BOOKING = "CANCEL A CAR BOOKING BY BOOKING ID";
    public static final String TITLE_USER_BOOKED_CARS = "ALL CARS BOOKED BY USER ID";

    public static final String TITLE_ACTIVE_BOOKINGS = "ALL ACTIVE BOOKINGS";
    public static final String TITLE_REGISTERED_USERS = "ALL REGISTERED USERS";

    public static final String TITLE_GREETING_MENU = "🚗 WELCOME TO THE CAR BOOKING MANAGEMENT SYSTEM 🚗";
    public static final String TITLE_MAIN_MENU = "MAIN MENU";
    public static final String TITLE_BOOKING_SUCCESS_MENU = "BOOKING CONFIRMATION MENU";
    public static final String TITLE_CANCELLATION_SUCCESS_MENU = "CANCELLATION CONFIRMATION MENU";


    public static final String SYSTEM_GUIDELINES_MENU = "GUIDELINES";
    public static final String USER_INPUT_MENU = "INPUT MENU";

    /**
     *      Displays the main greeting message for
     *      the Car booking application with a
     *      border.
     * */

    public static void displayCarBookingGreeting(){
        System.out.println(displayMenuBorder());
        displayCenteredMenuTitles(TITLE_GREETING_MENU);
        System.out.println(displayMenuBorder());
    }

    /**
     *      Displays the Main menu of the application
     *      listing all available options (1-9) that
     *      allow the user to navigate the system,
     *      view data, and initiate a Car booking
     * */

    public static void displayMainMenu() {
        displayCenteredMenuTitles(TITLE_MAIN_MENU);
        displayFormattedMessage("➡️","1 To Make a Car Booking (By User ID and Car Registration Number).");
        displayFormattedMessage("➡️","2 To Cancel a Car Booking (By Booking ID).");
        displayFormattedMessage("➡️","3 To Display All Available Cars for booking.");
        displayFormattedMessage("➡️", "4 To Display All Available Gasoline Cars for booking.");
        displayFormattedMessage("➡️", "5 To Display All Available Electric Cars for booking.");
        displayFormattedMessage("➡️","6 To Display All Active Bookings.");
        displayFormattedMessage("➡️", "7 To Display All Cars Booked by a User (By User ID).");
        displayFormattedMessage("➡️", "8 To Display All Registered Users.");
        displayFormattedMessage("➡️", "9 To View Main Menu.");
        displayFormattedMessage("➡️", "0 To Exit Application.");
    }

    /**
     *     Displays a formatted header for a
     *     given menu name with a border
     * */

    public static void displayResultsByMenuTitle(String menuName){
        System.out.println();
        System.out.println(displayMenuBorder());
        displayCenteredMenuTitles(menuName);
        System.out.println(displayMenuBorder());
        System.out.println();
    }

    /**
     *      Displays the main system prompt asking
     *      the user to input their menu item choice.
     * */

    public static void displayUserInput() {
        System.out.println();
        System.out.println(displayMenuBorder());
        displayCenteredMenuTitles(USER_INPUT_MENU);
        displayFormattedUserInput("➡️","Please enter the number for the option selected (To view MAIN MENU options press 9 or 0 to EXIT): ");
    }

    /**
     *      Displays the system's basic operating
     *      instructions and guidelines.
     *
     *      Guides the user on how to interact
     *      with the menu system and follow
     *      input prompts.
     */

    public static void displaySystemGuidelines() {
        displayCenteredMenuTitles(SYSTEM_GUIDELINES_MENU);
        displayFormattedMessage("➡️","Select an option from the MAIN MENU below and press ENTER.");
        displayFormattedMessage("➡️","Follow the prompts for specific inputs (i.e. creating a car booking, canceling a car booking).");
        System.out.println();
    }

    /**
     *       Displays instructions to the user
     *       when navigating the display cars
     *       booked by user id menu.
     *
     *       Guides user to input the
     *       correct format for a user id
     *       to retrieve cars booked by a
     *       user.
     * */

    public static void displayCarsBookedByUserIdGuidelines() {
        displayFormattedMessage("✅️","Please enter the user id from the registered users to display the cars booked by the user.");
    }

    /**
     *       Displays instructions to the user
     *       when navigating the booking
     *       menu.
     *
     *       Guides the user to input the
     *       correct format for a user
     *       id to begin the Car Booking.
     * */

    public static void displayUserIdBookingGuidelines() {
        displayFormattedMessage("✅️","Please enter the user id from the registered users to make a car booking.");
    }

    /**
     *       Displays error message to the user
     *       and prompts user to input
     *       a valid format for a
     *       user id.
     * */

    public static void displayUserIdErrorGuidelines() {
        displayFormattedMessage("⚠️","Please enter a valid user id.");
    }

    /**
     *       Displays error message to the user
     *       and prompts user to input
     *       a valid format for a
     *       booking id.
     * */

    public static void displayBookingIdErrorGuidelines() {
        displayFormattedMessage("⚠️","Please enter a valid booking id.");
    }

    /**
     *       Displays error message to the user
     *       and prompts user to input
     *       a valid format for a
     *       registration number.
     * */

    public static void displayRegistrationNumberErrorGuidelines() {
        displayFormattedMessage("⚠️","Please enter a valid registration number.");
    }

    /**
     *       Displays instructions to the user
     *       when navigating the booking
     *       menu.
     *
     * */

    public static void displayRegistrationNumberBookingGuidelines() {
        displayFormattedMessage("✅","Please enter the registration number from the available cars to complete a booking.");
    }

    /**
     *       Displays instructions to the user
     *       when navigating the cancel booking
     *       by booking id menu.
     *
     * */

    public static void displayCancelBookingByBookingIdGuidelines() {
        displayFormattedMessage("✅","Please enter a booking id from the active bookings to cancel a booking.");
    }

    /**
     *       Formats and displays the complete details
     *       of a single active car booking  to the console.
     * */

    public static void displayFormattedBookingDetails(Booking booking){

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");

        StringBuilder bookingDetailsFormatted = new StringBuilder();
        String indentation = alignOutputResultsByTabCount();

        bookingDetailsFormatted.append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking ID:             ").append(booking.getUserBookingID()).append("\n");
        bookingDetailsFormatted.append("\t".repeat(TAB_COUNT + 4) + "-".repeat(CLI_WIDTH / 4) + "\n");
        bookingDetailsFormatted.append(indentation).append("Booking time:           ").append(booking.getBookingTime().format(DATE_FORMATTER)).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking active:         ").append(booking.isBookingActive()).append("\n").append("\n");

        bookingDetailsFormatted.append(indentation).append("User ID:                ").append(booking.getUser().getUserId()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Name:                   ").append(booking.getUser().getFirstName()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Last Name:              ").append(booking.getUser().getLastName()).append("\n").append("\n");

        bookingDetailsFormatted.append(indentation).append("Registration Number:    ").append(booking.getCar().getRegistrationNumber()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Brand:                  ").append(booking.getCar().getBrand()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Rental Price Per Day:   ").append(booking.getCar().getRentalPricePerDay()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Fuel Type:              ").append(booking.getCar().getFuelType().toString()).append("\n").append("\n");
        System.out.println(bookingDetailsFormatted);

    }

    /**
     *       Formats and displays the complete details
     *       of a single user to the console.
     * */

    public static void displayFormattedUserDetails(User user){

        StringBuilder userDetailsFormatted = new StringBuilder();
        String indentation = alignOutputResultsByTabCount();

        userDetailsFormatted.append("\n");
        userDetailsFormatted.append(indentation).append("User ID:     ").append(user.getUserId()).append("\n");
        userDetailsFormatted.append("\t".repeat(TAB_COUNT + 4) + "-".repeat(CLI_WIDTH / 4) + "\n");
        userDetailsFormatted.append(indentation).append("Name:        ").append(user.getFirstName()).append("\n");
        userDetailsFormatted.append(indentation).append("Last Name:   ").append(user.getLastName()).append("\n").append("\n");
        System.out.println(userDetailsFormatted);
    }

    /**
     *       Formats and displays the complete details
     *       of a single car to the console.
     * */

    public static void displayFormattedCarDetails(Car Car){

        StringBuilder carDetailsFormatted = new StringBuilder();
        String indentation = alignOutputResultsByTabCount();

        carDetailsFormatted.append("\n");
        carDetailsFormatted.append(indentation).append("Registration Number:     ").append(Car.getRegistrationNumber()).append("\n");
        carDetailsFormatted.append("\t".repeat(TAB_COUNT + 4) + "-".repeat(CLI_WIDTH / 6) + "\n");
        carDetailsFormatted.append(indentation).append("Brand:                   ").append(Car.getBrand()).append("\n");
        carDetailsFormatted.append(indentation).append("Rental Price Per Day:    ").append(Car.getRentalPricePerDay()).append("\n");
        carDetailsFormatted.append(indentation).append("Fuel Type:               ").append(Car.getFuelType().toString()).append("\n").append("\n");
        System.out.println(carDetailsFormatted);
    }

    /**
     *       Displays and Centers menu titles
     * */

    private static void displayCenteredMenuTitles(String menuTitles){
        System.out.println(centerMenuTitles(menuTitles));
    }

    /**
     *        returns centered menu titles
     * */

    private static String centerMenuTitles(String menuTitle) {

        // Total amount of empty space available on both the left and right sides of the title
        int titlePadding = (displayMenuBorder().length() - menuTitle.length()) / 2;

        // [titlePadding spaces] + [title string]
        String centeredTitle = String.format("%" + titlePadding + "s%s", "", menuTitle);

        // centeredTitle appears on its own line
        return "\n" + centeredTitle + "\n";
    }

    /**
     *       Displays a centered message to the console with
     *       a specified icon type and message
     * */

    public static void displayFormattedMessage(String messageIcon, String message) {
        System.out.println(getFormattedMessage(messageIcon, message));
    }

    /**
     *       Returns a centered message to the console with
     *       a specified icon type and message
     * */

    private static String getFormattedMessage(String messageIcon, String message) {
        return alignOutputByTabCount() + messageIcon + " " + message;
    }

    /**
     *       Displays a centered prompt to the console with
     *       a specified icon type and message
     * */

    public static void displayFormattedUserInput(String symbol, String message) {
        System.out.print(getFormattedUserInput(symbol, message));
    }

    /**
     *      Returns a centered prompt with the specified icon type to
     *      the prompt content.
     * */

    private static String getFormattedUserInput(String messageIcon, String message) {
        return alignOutputByTabCount() + messageIcon + " " + message;
    }

    /**
     *      Solid border that spans the entire width of the
     *      defined by CLI_WIDTH constant.
     */

    private static String displayMenuBorder(){
        return "=".repeat(CLI_WIDTH);
    }

    /**
     *      Centers output to console, defined by TAB_COUNT constant.
     */

    private static String alignOutputByTabCount() {
        return "\t".repeat(TAB_COUNT);
    }

    /**
     *      Centers results for user, cars and booking output to console.
     */

    private static String alignOutputResultsByTabCount() {
        return "\t".repeat(TAB_COUNT + 4);
    }

}
