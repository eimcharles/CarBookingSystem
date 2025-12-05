package app;

import booking.Booking;
import booking.BookingService;
import car.Car;
import user.User;
import user.UserService;

import static app.CLIFormatUtility.*;

/**
 *      CLIDisplayUtility is responsible for fetching
 *      data and organizing it for display,
 *      delegating formatting to CLIFormatUtility
 *
 * */

public class CLIDisplayUtility {

    /**
     *        Retrieves all registered users from the
     *        UserService and prints them to the console
     *        using the standardized `displayFormattedUserDetails()` method.
     *
     *        If no users are found, a corresponding message
     *        is displayed using `displayFormattedMessage()` method.
     *
     *        The results are presented under the menu title
     *        defined by `TITLE_REGISTERED_USERS`.
     *
     *        @param userService
     *
     *        The UserService instance used to fetch the user data.
     */

    public static void displayAllRegisteredUsers(UserService userService){

        User[] users = userService.getUsers();

        if (users == null || users.length == 0){
            displayFormattedMessage("❌", "No users currently registered in the system");
            return;
        }

        for (User user : users) {
            displayFormattedUserDetails(user);
        }
    }

    /**
     *      Retrieves all currently available
     *      cars from the BookingService and
     *      prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the car is not tied to any active booking.
     *
     *      If no cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ALL_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the availability data.
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] availableCars = bookingService.getAllUnbookedCars();

        if (availableCars == null || availableCars.length == 0) {
            displayFormattedMessage("❌", "No cars currently available for booking");
            return;
        }

        for (Car availableCar : availableCars) {
            displayFormattedCarDetails(availableCar);
        }
    }

    /**
     *      Retrieves all currently available gas cars from the
     *      BookingService and prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the gas car is not tied to any active booking.
     *
     *      If no gas cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_GAS_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAllUnbookedGasCars();

        if (availableGasCars == null || availableGasCars.length == 0) {
            displayFormattedMessage("❌", "No gas cars currently available for booking");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            displayFormattedCarDetails(availableGasCar);
        }
    }

    /**
     *      Retrieves all currently available
     *      electric cars from the BookingService
     *      and prints them to the console using the
     *      standardized `displayFormattedCarDetails()` method.
     *
     *      Availability means the electric car is not tied to any active booking.
     *
     *      If no electric cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ELECTRIC_CARS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAllUnbookedElectricCars();

        if (availableElectricCars == null || availableElectricCars.length == 0) {
            displayFormattedMessage("❌", "No electric cars currently available for booking");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            displayFormattedCarDetails(availableEelectricCar);
        }
    }

    /**
     *      Retrieves all active
     *      bookings from the BookingService
     *      and prints them to the console using the
     *      standardized `displayFormattedBookingDetails()` method.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed
     *      using `displayFormattedMessage()` method.
     *
     *      The results are presented under the menu title
     *      defined by `TITLE_ALL_BOOKINGS`.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the data.
     *
     */

    public static void displayAllActiveBookings(BookingService bookingService){

        Booking[] allBookings = bookingService.getBookings();

        if (allBookings == null || allBookings.length == 0){
            displayFormattedMessage("❌", "No bookings currently registered in the system");
            return;
        }


        for (Booking allBooking : allBookings) {
            displayFormattedBookingDetails(allBooking);
        }
    }

    public static void formatAndDisplayUserBookedCars(Car[] bookedCars, String validatedUserId){

        if (bookedCars == null || bookedCars.length == 0){

            // No bookings for the given user id
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            displayFormattedMessage("❌", "User ID '" + validatedUserId + "' has no active car bookings.");

        } else {

            // Display and format user booked car results
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            for (Car bookedCar : bookedCars) {
                displayFormattedCarDetails(bookedCar);
            }
        }
    }

}
