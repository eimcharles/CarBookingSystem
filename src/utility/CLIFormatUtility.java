package utility;

import booking.Booking;
import user.User;
import car.Car;

public class CLIFormatUtility {

    private static final int CLI_WIDTH = 230;
    private static final int TAB_COUNT = 18;

    public static final String TITLE_REGISTERED_USERS = "ALL REGISTERED USERS";
    public static final String TITLE_ALL_CARS = "ALL AVAILABLE CARS";
    public static final String TITLE_GAS_CARS = "AVAILABLE GAS CARS";
    public static final String TITLE_ELECTRIC_CARS = "AVAILABLE ELECTRIC CARS";
    public static final String TITLE_ALL_BOOKINGS = "ALL ACTIVE BOOKINGS";
    public static final String TITLE_MAKE_BOOKING = "BOOKING BY USER ID AND REGISTRATION NUMBER";
    public static final String TITLE_USER_BOOKED_CARS = "CARS BOOKED BY SPECIFIC USER";

    public static final String TITLE_MAIN_MENU = "MAIN MENU";
    public static final String TITLE_INPUT_MENU = "INPUT MENU";

    public static final String TITLE_GREETING_MENU = "🚗 WELCOME TO THE CAR BOOKING MANAGEMENT SYSTEM 🚗";


    public static void displayMainMenu() {
        displayMenuSeparation();
        displayCenteredMenu(TITLE_MAIN_MENU);
        displayIndentedMessage("➡️","1 To Display All Available Cars");
        displayIndentedMessage("➡️", "2 To Display Available Gasoline Cars");
        displayIndentedMessage("➡️", "3 To Display Available Electric Cars");
        displayIndentedMessage("➡️","4 To Make a Car Booking (By User ID and Car Registration Number)");
        displayIndentedMessage("➡️", "5 To Display Cars Booked by a Specific User");
        displayIndentedMessage("➡️","6 To Display All Active Bookings");
        displayIndentedMessage("➡️", "7 To Display All Registered Users");
        displayIndentedMessage("➡️", "8 To Exit Application");
        displayMenuSeparation();
    }

    public static void displayResultsMenu(String menuName){
        displayMenuSeparation();
        displayCenteredMenu(menuName);
    }

    public static void displayUserInputMenu() {
        displayCenteredMenu(TITLE_INPUT_MENU);
        displayIndentedMessage("➡️","Enter the number for a menu option (1-8) and press ENTER");
        displayIndentedUserInput("➡️","Please enter your choice (1-8): ");
    }

    public static void displayGreeting(){
        displayCenteredMenu(TITLE_GREETING_MENU);
    }

    public static void displayInteractionMenu() {
        displayIndentedMessage("➡️","Enter the number for a menu option (1-8) and press ENTER");
        displayIndentedMessage("➡️","Follow the prompts for specific inputs (i.e. creating a Car Booking).");
    }

    public static void displayUserIDInteractionMenu() {
        displayIndentedMessage("⚠️","Please select a USER ID from all the registered users menu (e.g., '8ca51d2b-aaaf-4bf2-834a-e02964e10fc3')");
    }

    public static void displayRegistrationNumberInteractionMenu() {
        displayIndentedMessage("⚠️","Please select a REGISTRATION NUMBER from the available cars menu (e.g., '123_3')");
    }

    public static void displayBookingDetailsFormatted(Booking booking){

        StringBuilder bookingDetailsFormatted = new StringBuilder();
        String indentation = getIndentedMenuItem();

        bookingDetailsFormatted.append(indentation).append("Booking ID:             ").append(booking.getUserBookingID()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking time:           ").append(booking.getBookingTime()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking Cancelled:      ").append(booking.isBookingCancelled()).append("\n");
        System.out.println(bookingDetailsFormatted);

        displayUserDetailsFormatted(booking.getUser());
        displayCarDetailsFormatted(booking.getCar());
    }

    public static void displayUserDetailsFormatted(User user){

        StringBuilder userDetailsFormatted = new StringBuilder();
        String indentation = getIndentedMenuItem();

        userDetailsFormatted.append(indentation).append("Name:        ").append(user.getName()).append("\n");
        userDetailsFormatted.append(indentation).append("Last Name:   ").append(user.getLastName()).append("\n");
        userDetailsFormatted.append(indentation).append("User ID:     ").append(user.getUserId()).append("\n");

        System.out.println(userDetailsFormatted);
    }

    public static void displayCarDetailsFormatted(Car Car){

        StringBuilder carDetailsFormatted = new StringBuilder();
        String indentation = getIndentedMenuItem();

        carDetailsFormatted.append(indentation).append("Manufacturer:            ").append(Car.getManufacturer()).append("\n");
        carDetailsFormatted.append(indentation).append("Fuel Type:               ").append(Car.getFuelType().toString()).append("\n");
        carDetailsFormatted.append(indentation).append("Registration Number:     ").append(Car.getRegistrationNumber()).append("\n");
        carDetailsFormatted.append(indentation).append("Rental Price Per Day:    ").append(Car.getRentalPricePerDay()).append("\n");

        System.out.println(carDetailsFormatted);
    }

    private static void displayCenteredMenu(String title){
        System.out.println(getCenteredMenuTitle(title));
    }

    private static String getCenteredMenuTitle(String title) {
        int titlePadding = (getMenuSeparation().length() - title.length()) / 2;
        String centeredTitle = String.format("%" + titlePadding + "s%s", "", title);
        return "\n" + centeredTitle + "\n";
    }

    private static void displayMenuSeparation(){
        System.out.println("\n" + getMenuSeparation());
    }

    private static String getMenuSeparation(){
        return "=".repeat(CLI_WIDTH);
    }

    private static String getIndentedMenuItem() {
        return "\t".repeat(TAB_COUNT);
    }

    private static String getIndentedMessage(String symbol, String message) {
        return getIndentedMenuItem() + symbol + " " + message;
    }

    public static void displayIndentedMessage(String symbol, String message) {
        System.out.println(getIndentedMessage(symbol, message));
    }

    private static String getIndentedUserInput(String symbol, String message) {
        return getIndentedMenuItem() + symbol + " " + message;
    }

    public static void displayIndentedUserInput(String symbol, String message) {
        System.out.print(getIndentedUserInput(symbol, message));
    }

}
