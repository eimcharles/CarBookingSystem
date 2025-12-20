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
     *        displayAllRegisteredUsers() retrieves all registered in the system
     *
     *        If no users are found, a corresponding message is displayed.
     */

    public static void displayAllRegisteredUsers(UserService userService){

        User[] registeredUsers = userService.getUsers();

        if (registeredUsers == null || registeredUsers.length == 0){
            displayFormattedMessage("❌", "The system has no users currently registered.");
            return;
        }

        for (User user : registeredUsers) {
            displayFormattedUserDetails(user);
        }
    }

    /**
     *      displayAllAvailableCars() retrieves all available cars in the system
     *
     *      Availability: the car is not associated to any active booking.
     *
     *      If no cars are available, a corresponding message is displayed
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] allAvailableCars = bookingService.getAllAvailableCars();

        if (allAvailableCars == null || allAvailableCars.length == 0) {
            displayFormattedMessage("❌", "The system has no cars currently available for booking.");
            return;
        }

        for (Car availableCar : allAvailableCars) {
            displayFormattedCarDetails(availableCar);
        }
    }

    /**
     *      displayAllAvailableGasCars retrieves all available gas cars from the system
     *
     *      Availability: the gas car is not associated to any active booking.
     *
     *      If no gas cars are available, a corresponding message is displayed
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAllAvailableGasCars();

        if (availableGasCars == null || availableGasCars.length == 0) {
            displayFormattedMessage("❌", "The system has no gas cars currently available for booking.");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            displayFormattedCarDetails(availableGasCar);
        }
    }

    /**
     *      displayAllAvailableElectricCars() retrieves all available electric cars from the system
     *
     *      Availability: the electric car is not associated to any active booking.
     *
     *      If no electric cars are available, a corresponding message is displayed
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAllAvailableElectricCars();

        if (availableElectricCars == null || availableElectricCars.length == 0) {
            displayFormattedMessage("❌", "The system has no electric cars currently available for booking.");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            displayFormattedCarDetails(availableEelectricCar);
        }
    }

    /**
     *      displayAllActiveBookings() retrieves all active bookings from the system
     *
     *      If no active bookings are found, a corresponding message is displayed
     */

    public static void displayAllActiveBookings(BookingService bookingService){

        Booking[] allActiveBookings = bookingService.getAllActiveBookings();

        if (allActiveBookings == null || allActiveBookings.length == 0){
            displayFormattedMessage("❌", "The system has no active bookings currently registered.");
            return;
        }


        for (Booking allBooking : allActiveBookings) {
            displayFormattedBookingDetails(allBooking);
        }
    }

    /**
     *      displayUserBookedCars() retrieves user booked cars by user id
     *
     *      If a user has no active bookings a corresponding message is displayed
     */

    public static void displayUserBookedCars(Car[] bookedCars, String validatedUserId){

        if (bookedCars == null || bookedCars.length == 0){

            // No bookings for the given user id
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            displayFormattedMessage("❌", "The user with user ID '" + validatedUserId + " has no cars currently booked.");

        } else {

            // Display and format user booked car results
            displayResultsByMenuTitle(TITLE_USER_BOOKED_CARS);
            for (Car bookedCar : bookedCars) {
                displayFormattedCarDetails(bookedCar);
            }
        }
    }

}
