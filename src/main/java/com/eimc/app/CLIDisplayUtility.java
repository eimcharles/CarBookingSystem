package com.eimc.app;

import com.eimc.booking.Booking;
import com.eimc.booking.BookingService;
import com.eimc.car.Car;
import com.eimc.user.User;
import com.eimc.user.UserService;

import static com.eimc.app.CLIFormatUtility.*;

/**
 *      CLIDisplayUtility is a utility class that is part of the CarBookingApplication presentation layer.
 *
 *      It is responsible for fetching data and organizing it for display,
 *      delegating formatting to CLIFormatUtility.
 * */

public class CLIDisplayUtility {

    /**
     *        displayAllRegisteredUsers retrieves all registered users from the
     *        UserService class and prints them to the console
     *        using the `displayFormattedUserDetails()` method.
     *
     *        If no users are found, a corresponding message
     *        is displayed using `displayFormattedMessage()` method.
     *
     *        @param userService
     *
     *        The UserService instance used to fetch the user data.
     */

    public static void displayAllRegisteredUsers(UserService userService){

        User[] registeredUsers = userService.getUsers();

        if (registeredUsers == null || registeredUsers.length == 0){
            displayFormattedMessage("❌", "No registeredUsers currently registered in the system");
            return;
        }

        for (User user : registeredUsers) {
            displayFormattedUserDetails(user);
        }
    }

    /**
     *      displayAllAvailableCars retrieves all available cars from the
     *      BookingService class and prints them to the console
     *      using the `displayFormattedCarDetails()` method.
     *
     *      Availability: the car is not associated to any active booking.
     *
     *      If no cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the availability data.
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] allAvailableCars = bookingService.getAllAvailableCars();

        if (allAvailableCars == null || allAvailableCars.length == 0) {
            displayFormattedMessage("❌", "No cars currently available for booking");
            return;
        }

        for (Car availableCar : allAvailableCars) {
            displayFormattedCarDetails(availableCar);
        }
    }

    /**
     *      displayAllAvailableGasCars retrieves all available gas cars from the
     *      BookingService class and prints them to the console
     *      using the `displayFormattedCarDetails()` method.
     *
     *      Availability: the gas car is not associated to any active booking.
     *
     *      If no gas cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAllAvailableGasCars();

        if (availableGasCars == null || availableGasCars.length == 0) {
            displayFormattedMessage("❌", "No gas cars currently available for booking");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            displayFormattedCarDetails(availableGasCar);
        }
    }

    /**
     *      displayAllAvailableElectricCars retrieves all  available electric cars from the
     *      BookingService class and prints them to the console using the
     *      `displayFormattedCarDetails()` method.
     *
     *      Availability: the electric car is not associated to any active booking.
     *
     *      If no electric cars are available, a corresponding message
     *      is displayed using `displayFormattedMessage()` method.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAllAvailableElectricCars();

        if (availableElectricCars == null || availableElectricCars.length == 0) {
            displayFormattedMessage("❌", "No electric cars currently available for booking");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            displayFormattedCarDetails(availableEelectricCar);
        }
    }

    /**
     *      displayAllActiveBookings retrieves all active bookings from the
     *      BookingService class and prints them to the console using the
     *      `displayFormattedBookingDetails()` method.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed
     *      using `displayFormattedMessage()` method.
     *
     *      @param bookingService
     *
     *      The BookingService instance used to fetch the data.
     */

    public static void displayAllActiveBookings(BookingService bookingService){

        Booking[] allActiveBookings = bookingService.getAllActiveBookings();

        if (allActiveBookings == null || allActiveBookings.length == 0){
            displayFormattedMessage("❌", "No bookings currently registered in the system");
            return;
        }


        for (Booking allBooking : allActiveBookings) {
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
