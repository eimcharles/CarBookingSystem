package utility;

import booking.Booking;
import user.User;
import car.Car;

/**
 *      CLIFormatUtility is a utility class dedicated
 *      to formatting and displaying all output within the
 *      CarBookingCLI class.
 */

public class CLIFormatUtility {

    private static final int CLI_WIDTH = 240;
    private static final int TAB_COUNT = 18;

    public static final String TITLE_ALL_CARS = "AVAILABLE CARS";
    public static final String TITLE_GAS_CARS = "AVAILABLE GAS CARS";
    public static final String TITLE_ELECTRIC_CARS = "AVAILABLE ELECTRIC CARS";

    public static final String TITLE_MAKE_BOOKING = "MAKE A CAR BOOKING BY USER ID AND REGISTRATION NUMBER";
    public static final String TITLE_USER_BOOKED_CARS = "CARS BOOKED BY USER ID";

    public static final String TITLE_ALL_BOOKINGS = "ACTIVE BOOKINGS";
    public static final String TITLE_REGISTERED_USERS = "REGISTERED USERS";

    public static final String TITLE_GREETING_MENU = "🚗 WELCOME TO THE CAR BOOKING MANAGEMENT SYSTEM 🚗";
    public static final String TITLE_MAIN_MENU = "MAIN MENU";
    public static final String SYSTEM_GUIDELINES_MENU = "GUIDELINES";
    public static final String USER_INPUT_MENU = "INPUT MENU";


    public static void displayCarBookingGreeting(){
        System.out.println(displayMenuBorder());
        displayCenteredMenuTitles(TITLE_GREETING_MENU);
        System.out.println(displayMenuBorder());
    }

    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */

    public static void displayMainMenu() {
        displayCenteredMenuTitles(TITLE_MAIN_MENU);
        displayFormattedMessage("➡️","1 To Display All Available Cars");
        displayFormattedMessage("➡️", "2 To Display Available Gasoline Cars");
        displayFormattedMessage("➡️", "3 To Display Available Electric Cars");
        displayFormattedMessage("➡️","4 To Make a Car Booking (By User ID and Car Registration Number)");
        displayFormattedMessage("➡️", "5 To Display Cars Booked by User (By User ID)");
        displayFormattedMessage("➡️","6 To Display All Active Bookings");
        displayFormattedMessage("➡️", "7 To Display All Registered Users");
        displayFormattedMessage("➡️", "8 To View Main Menu");
        displayFormattedMessage("➡️", "9 To Exit Application");
    }

    public static void displayResultsByMenuTitle(String menuName){
        System.out.println();
        System.out.println(displayMenuBorder());
        displayCenteredMenuTitles(menuName);
        System.out.println(displayMenuBorder());
        System.out.println();
    }

    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */


    public static void displayUserInput() {
        displayCenteredMenuTitles(USER_INPUT_MENU);
        displayFormattedUserInput("➡️","Please enter your choice (To view MAIN MENU options press 8): ");
    }

    public static void displaySystemGuidelines() {
        displayCenteredMenuTitles(SYSTEM_GUIDELINES_MENU);
        displayFormattedMessage("➡️","Enter the number for a menu option (1-9) and press ENTER");
        displayFormattedMessage("➡️","Follow the prompts for specific inputs (i.e. creating a Car Booking).");
    }


    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */

    public static void displayCarsBookedByUserIdGuidelines() {
        displayFormattedMessage("✅️","Please select a USER ID (e.g. 'b10d126a-3608-4980-9f9c-aa179f5cebc3') from the REGISTERED USERS to DISPLAY CARS BOOKED BY USER ID");
    }

    public static void displayUserIdBookingGuidelines() {
        displayFormattedMessage("✅️","Please select a USER ID (e.g. 'b10d126a-3608-4980-9f9c-aa179f5cebc3') from the REGISTERED USERS to MAKE A BOOKING");
    }

    public static void displayRegistrationNumberBookingGuidelines() {
        displayFormattedMessage("✅","Please select a REGISTRATION NUMBER from the AVAILABLE CARS MENU (e.g., '123_3')");
    }

    /**
     *       TODO add documentation
     *
     *
     *
     *
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

    public static void displayFormattedUserDetails(User user){

        StringBuilder userDetailsFormatted = new StringBuilder();
        String indentation = alignOutputByTabCount();

        userDetailsFormatted.append(indentation).append("Name:        ").append(user.getName()).append("\n");
        userDetailsFormatted.append(indentation).append("Last Name:   ").append(user.getLastName()).append("\n");
        userDetailsFormatted.append(indentation).append("User ID:     ").append(user.getUserId()).append("\n");

        System.out.println(userDetailsFormatted);
    }

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
     *       TODO add documentation
     *
     *
     *
     *
     * */

    private static void displayCenteredMenuTitles(String menuTitles){
        System.out.println(centerMenuTitles(menuTitles));
    }

    private static String centerMenuTitles(String menuTitle) {

        // Total amount of empty space available on both the left and right sides of the title
        int titlePadding = (displayMenuBorder().length() - menuTitle.length()) / 2;

        // [titlePadding spaces] + [title string]
        String centeredTitle = String.format("%" + titlePadding + "s%s", "", menuTitle);

        // centeredTitle appears on its own line
        return "\n" + centeredTitle + "\n";
    }


    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */

    public static void displayFormattedMessage(String messageIcon, String message) {
        System.out.println(getFormattedMessage(messageIcon, message));
    }

    private static String getFormattedMessage(String messageIcon, String message) {
        return alignOutputByTabCount() + messageIcon + " " + message;
    }

    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */

    public static void displayFormattedUserInput(String symbol, String message) {
        System.out.print(getFormattedUserInput(symbol, message));
    }

    private static String getFormattedUserInput(String messageIcon, String message) {
        return alignOutputByTabCount() + messageIcon + " " + message;
    }

    /**
     *       TODO add documentation
     *
     *
     *
     *
     * */

    private static String displayMenuBorder(){
        return "=".repeat(CLI_WIDTH);
    }

    private static String alignOutputByTabCount() {
        return "\t".repeat(TAB_COUNT);
    }

}
