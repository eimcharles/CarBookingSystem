package App;

import Booking.Booking;
import Booking.BookingService;
import Car.Car;
import Car.CarService;
import User.User;
import User.UserService;

import java.util.Scanner;
import java.util.UUID;

/**
 *      Driver class for Car Booking System CLI
 *
 *      TODO: Implement Driver Class
 * */

public class CarBookingCLI {


    public static void makeACarBooking(BookingService bookingService, CarService carService, Scanner scanner){

        displayAllAvailableCars(bookingService);
        ///  TODO implement logic
    }

    /**
     *      Guides the user to input a specific User ID,
     *      validating the input format as a UUID.
     *
     *       It then retrieves all cars currently booked
     *      by that validated user from the BookingService
     *      and prints them to the console.
     *
     *      If the user has no active bookings, a
     *      corresponding message is displayed.
     *
     *      @param userService
     *
     *      The UserService instance used to display
     *      available user IDs.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch
     *      the user's booked car data.
     *
     *      @param scanner
     *
     *      The Scanner instance used to handle
     *      user input for the User ID.
     */

    public static void displayUserBookedCars(UserService userService, BookingService bookingService, Scanner scanner){

        String userIdInput;
        UUID userId = null;
        // Set the user input to invalid first
        boolean isValidInput = false;

        // Display Feature Header and list all available users for the user to pick an ID from.
        displayAllUsers(userService);

        do {

            // Prompt user for input
            System.out.println("\n============================================================================================");
            System.out.print("➡️ Please enter the User ID (e.g., 'U1001') to view user booked cars: ");
            userIdInput = scanner.nextLine().trim();
            System.out.println("============================================================================================\n");

            // Check 1: Handle empty input
            if (userIdInput.isEmpty()) {
                System.out.println("\n============================================================================================");
                System.out.println("⚠️ User ID cannot be empty. Please try again.");
                System.out.println("============================================================================================\n");

                // Skips the rest of the current loop body
                continue;
            }


            try {

                // Check 2: Attempt to convert the input string to a UUID object.
                userId = UUID.fromString(userIdInput);

                // If the conversion succeeds, set flag to true to exit loop
                isValidInput = true;

            } catch (IllegalArgumentException e){

                // If conversion fails, print error message and loop continues (isValidInput remains false)
                System.out.println("\n============================================================================================");
                System.out.println("❌ Invalid User ID format: '" + userIdInput + "'. Please enter a valid UUID string.");
                System.out.println("============================================================================================\n");

                // List all available users for the user to pick an ID from again.
                displayAllUsers(userService);

            }

        } while (!isValidInput);



        Car[] bookedCars = bookingService.getUserBookedCarsByUserId(userId);

        if (bookedCars == null || bookedCars.length == 0){

            // Fetch and Display Results (only executed once valid UUID is guaranteed) ---
            System.out.println("\n===============================  RESULTS  ==================================================");
            System.out.println("❌ User ID '" + userIdInput + "' has no active car bookings.");
            System.out.println("============================================================================================\n");

        } else {

            // Booked cars were found
            System.out.println("\n===============================  RESULTS  =================================================");
            for (Car bookedCar : bookedCars) {
                System.out.println(bookedCar);
            }

            System.out.println("============================================================================================\n");

        }
    }

    /**
     *      Retrieves all currently available
     *      cars that run on gasoline from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the gas car is
     *      not tied to any active booking.
     *
     *       If no gas cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAvailableGasCars();

        if (availableGasCars == null || availableGasCars.length == 0) {
            System.out.println("\n==========================================  AVAILABLE GAS CARS  ================================================");
            System.out.println("❌ No gas cars currently available for booking");
            return;
        }

        System.out.println("\n==========================================  AVAILABLE GAS CARS  ================================================");
        for (Car availableGasCar : availableGasCars) {
            System.out.println(availableGasCar);
        }

        System.out.println("================================================================================================================\n");

    }

    /**
     *      Retrieves all currently available
     *      cars that run on electricity from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the electric car is
     *       not tied to any active booking.
     *
     *      If no electric cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        if (availableElectricCars == null || availableElectricCars.length == 0) {
            System.out.println("\n==========================================  AVAILABLE ELECTRIC CARS  ============================================");
            System.out.println("❌ No electric cars currently available for booking");
            System.out.println("=================================================================================================================\n");
            return;
        }

        System.out.println("\n==========================================  AVAILABLE ELECTRIC CARS  ============================================");
        for (Car availableEelectricCar : availableElectricCars) {
            System.out.println(availableEelectricCar);
        }

        System.out.println("=================================================================================================================\n");

    }

    /**
     *          Retrieves all currently available
     *          cars from the BookingService and
     *          prints them to the console.
     *
     *          Availability means the car is
     *          not tied to any active booking.
     *
     *          If no cars are available, a
     *          corresponding message is displayed.
     *
     *          @param bookingService
     *
     *          The BookingService instance
     *          used to fetch the availability data.
     *
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] availableCars = bookingService.getAllAvailableCars();

        if (availableCars == null || availableCars.length == 0) {
            System.out.println("\n==========================================  AVAILABLE CARS  =====================================================");
            System.out.println("❌ No cars currently available for booking");
            System.out.println("=================================================================================================================\n");

            return;
        }

        System.out.println("\n==========================================  AVAILABLE CARS  =====================================================");
        for (Car availableCar : availableCars) {
            System.out.println(availableCar);
        }

        System.out.println("=================================================================================================================\n");

    }

    /**
     *      Retrieves all active
     *      bookings from the BookingService
     *      and prints them to the console.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the data.
     *
     */

    public static void displayAllBooking(BookingService bookingService){

        Booking[] allBookings = bookingService.getAllBookings();

        if (allBookings == null || allBookings.length == 0){
            System.out.println("\n==========================================  ACTIVE BOOKINGS  ===================================================");
            System.out.println("❌ No bookings currently registered in the system");
            System.out.println("================================================================================================================\n");
            return;
        }

        System.out.println("\n==========================================  ACTIVE BOOKINGS  ==================================================");
        for (Booking allBooking : allBookings) {
            System.out.println(allBooking);
        }

        System.out.println("================================================================================================================\n");

    }

    /**
     *          Retrieves all registered
     *          users from the UserService
     *          and prints them to the console.
     *
     *          If no users are found,
     *          a corresponding message is displayed.
     *
     *          @param userService
     *
     *          The UserService instance
     *          used to fetch the data.
     *          
     */

    public static void displayAllUsers(UserService userService){

        User[] users = userService.getAllUsers();

        if (users == null || users.length == 0){

            System.out.println("\n=================================  USERS  ==================================================");
            System.out.println("❌ No users currently registered in the system");
            System.out.println("============================================================================================\n");
            return;

        }

        System.out.println("\n=================================  USERS  ==================================================");
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("============================================================================================\n");

    }
}
