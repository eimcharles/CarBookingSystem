package com.eimc.app;

import com.eimc.booking.Booking;
import com.eimc.user.User;
import com.eimc.car.Car;

/**
 *      CLIFormatUtility is a utility class that is part of the CarBookingApplication presentation layer.
 *
 *      It is responsible for formatting and displaying all output within the
 *      CarBookingCLI class.
 */

public class CLIFormatUtility {

    private static final int CLI_WIDTH = 240;
    private static final int TAB_COUNT = 18;

    public static final String TITLE_ALL_CARS = "AVAILABLE CARS";
    public static final String TITLE_GAS_CARS = "AVAILABLE GAS CARS";
    public static final String TITLE_ELECTRIC_CARS = "AVAILABLE ELECTRIC CARS";

    public static final String TITLE_MAKE_BOOKING = "MAKE A CAR BOOKING BY USER ID AND REGISTRATION NUMBER";
    public static final String TITLE_CANCEL_BOOKING = "CANCEL A CAR BOOKING BY BOOKING ID";
    public static final String TITLE_USER_BOOKED_CARS = "CARS BOOKED BY USER ID";

    public static final String TITLE_ACTIVE_BOOKINGS = "ACTIVE BOOKINGS";
    public static final String TITLE_REGISTERED_USERS = "REGISTERED USERS";

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
        displayFormattedMessage("➡️","1 To Make a Car Booking (By User ID and Car Registration Number)");
        displayFormattedMessage("➡️","2 To Cancel a Car Booking (By Booking ID)");
        displayFormattedMessage("➡️","3 To Display All Available Cars");
        displayFormattedMessage("➡️", "4 To Display Available Gasoline Cars");
        displayFormattedMessage("➡️", "5 To Display Available Electric Cars");
        displayFormattedMessage("➡️","6 To Display All Active Bookings");
        displayFormattedMessage("➡️", "7 To Display Cars Booked by User (By User ID)");
        displayFormattedMessage("➡️", "8 To Display All Registered Users");
        displayFormattedMessage("➡️", "9 To View Main Menu");
        displayFormattedMessage("➡️", "0 To Exit Application");
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
        displayFormattedUserInput("➡️","Please enter your choice (To view MAIN MENU options press 9 or 0 to EXIT.): ");
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
        displayFormattedMessage("➡️","Enter the number for a menu option (1 to 9) and press ENTER.");
        displayFormattedMessage("➡️","Follow the prompts for specific inputs (i.e. creating a Car Booking, canceling a Car Booking).");
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
        displayFormattedMessage("✅️","Please enter a USER ID (e.g. 'b10d126a-3608-4980-9f9c-aa179f5cebc3') from the REGISTERED USERS to DISPLAY CARS BOOKED BY USER.");
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
        displayFormattedMessage("✅️","Please enter a USER ID (e.g. 'b10d126a-3608-4980-9f9c-aa179f5cebc3') from the REGISTERED USERS to MAKE A BOOKING.");
    }

    /**
     *       Displays instructions to the user
     *       when navigating the booking
     *       menu.
     *
     *       Guides the user to input the
     *       correct format for a Car
     *       registration number to complete
     *       the Car Booking.
     * */

    public static void displayRegistrationNumberBookingGuidelines() {
        displayFormattedMessage("✅","Please enter a REGISTRATION NUMBER from the AVAILABLE CARS MENU (e.g., '123_3').");
    }

    /**
     *       Displays instructions to the user
     *       when navigating the cancel booking
     *       by booking id menu.
     *
     *       Guides the user to input the
     *       correct format for a booking
     *       id number to cancel a booking.
     * */

    public static void displayCancelBookingByBookingIdGuidelines() {
        displayFormattedMessage("✅","Please enter a BOOKING ID from the ACTIVE BOOKINGS MENU to cancel a booking.");
    }

    /**
     *       Formats and displays the complete details
     *       of a single active car booking  to the console.
     * */

    public static void displayFormattedBookingDetails(Booking booking){

        StringBuilder bookingDetailsFormatted = new StringBuilder();
        String indentation = alignOutputByTabCount();

        bookingDetailsFormatted.append(indentation).append("Booking ID:             ").append(booking.getUserBookingID()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking time:           ").append(booking.getBookingTime()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking Cancelled:      ").append(booking.isBookingCancelled()).append("\n");
        System.out.println(bookingDetailsFormatted);

        displayFormattedUserDetails(booking.getUser());
        displayFormattedCarDetails(booking.getCar());
    }

    /**
     *       Formats and displays the complete details
     *       of a single user to the console.
     * */

    public static void displayFormattedUserDetails(User user){

        StringBuilder userDetailsFormatted = new StringBuilder();
        String indentation = alignOutputByTabCount();

        userDetailsFormatted.append(indentation).append("Name:        ").append(user.getName()).append("\n");
        userDetailsFormatted.append(indentation).append("Last Name:   ").append(user.getLastName()).append("\n");
        userDetailsFormatted.append(indentation).append("User ID:     ").append(user.getUserId()).append("\n");

        System.out.println(userDetailsFormatted);
    }

    /**
     *       Formats and displays the complete details
     *       of a single car to the console.
     * */

    public static void displayFormattedCarDetails(Car Car){

        StringBuilder carDetailsFormatted = new StringBuilder();
        String indentation = alignOutputByTabCount();

        carDetailsFormatted.append(indentation).append("Manufacturer:            ").append(Car.getManufacturer()).append("\n");
        carDetailsFormatted.append(indentation).append("Fuel Type:               ").append(Car.getFuelType().toString()).append("\n");
        carDetailsFormatted.append(indentation).append("Registration Number:     ").append(Car.getRegistrationNumber()).append("\n");
        carDetailsFormatted.append(indentation).append("Rental Price Per Day:    ").append(Car.getRentalPricePerDay()).append("\n");

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

}
