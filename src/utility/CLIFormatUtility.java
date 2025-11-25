package utility;

import booking.Booking;
import user.User;
import car.Car;

public class CLIFormatUtility {

    private static final int CLI_WIDTH = 230;
    private static final int TAB_COUNT = 18;

    public static final String TITLE_REGISTERED_USERS = "REGISTERED USERS";
    public static final String TITLE_ALL_CARS = "ALL AVAILABLE CARS";
    public static final String TITLE_GAS_CARS = "AVAILABLE GAS CARS";
    public static final String TITLE_ELECTRIC_CARS = "AVAILABLE ELECTRIC CARS";
    public static final String TITLE_ALL_BOOKINGS = "ALL ACTIVE BOOKINGS";
    public static final String TITLE_MAKE_BOOKING = "MAKE BOOKING";
    public static final String TITLE_USER_BOOKED_CARS = "USER BOOKED CARS";


    public static void displayMainMenu() {
        displayMenuSeparation();
        displayCenteredMenu("Main menu");
        displayIndentedMessage("➡️","1 To Display All Available Cars");
        displayIndentedMessage("➡️", "2 To Display Available Gasoline Cars");
        displayIndentedMessage("➡️", "3 To Display Available Electric Cars");
        displayIndentedMessage("➡️","4 To Make a Car Booking (By Car Registration Number and User ID");
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
        displayCenteredMenu("Input menu");
        displayIndentedMessage("➡️","Enter the number for a menu option (1-8) and press ENTER");
        displayIndentedUserInput("➡️","Please enter your choice (1-8): ");
    }

    public static void displayGreeting(){
        displayCenteredMenu("🚗 Welcome to the car booking management system 🚗");
    }

    public static void displayInteractionMenu() {
        displayIndentedMessage("➡️","Enter the number for a menu option (1-8) and press ENTER");
        displayIndentedMessage("➡️","Follow the prompts for specific inputs (i.e. creating a Car Booking).");
    }

    public static void displayBookingDetailsFormatted(Booking booking){

        StringBuilder bookingDetailsFormatted = new StringBuilder();
        String indentation = getIndentedMenuItem();

        bookingDetailsFormatted.append(indentation).append("Booking ID:        ").append(booking.getUserBookingID()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking time:      ").append(booking.getBookingTime()).append("\n");
        bookingDetailsFormatted.append(indentation).append("Booking Cancelled:    ").append(booking.isBookingCancelled()).append("\n");
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

    public static void displayCenteredMenu(String title){
        System.out.println(getCenteredMenuTitle(title));
    }

    public static String getCenteredMenuTitle(String title) {
        int titlePadding = (getMenuSeparation().length() - title.length()) / 2;
        String centeredTitle = String.format("%" + titlePadding + "s%s", "", title);
        return "\n" + centeredTitle + "\n";
    }

    public static void displayMenuSeparation(){
        System.out.println("\n" + getMenuSeparation());
    }

    public static String getMenuSeparation(){
        return "=".repeat(CLI_WIDTH);
    }

    public static String getIndentedMenuItem() {
        return "\t".repeat(TAB_COUNT);
    }

    public static String getIndentedMessage(String symbol, String message) {
        return getIndentedMenuItem() + symbol + " " + message;
    }

    public static void displayIndentedMessage(String symbol, String message) {
        System.out.println(getIndentedMessage(symbol, message));
    }

    public static String getIndentedUserInput(String symbol, String message) {
        return getIndentedMenuItem() + symbol + " " + message;
    }

    public static void displayIndentedUserInput(String symbol, String message) {
        System.out.print(getIndentedUserInput(symbol, message));
    }

}
