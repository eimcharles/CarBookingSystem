package Booking;

import Car.Car;
import Car.CarService;
import User.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 *      Service class for managing Booking objects.
 *      Contains business logic related to bookings.
 *
 *      TODO : implement booking Service business logic
 */

public class BookingService {

    private final BookingDAO bookingDAO;
    private final CarService carService;

    public BookingService(BookingDAO bookingDAO, CarService carService) {
        this.bookingDAO = bookingDAO;
        this.carService = carService;
    }

    public UUID addCarBooking(User user, String registrationNumber) {

        // Check Car Availability
        if (isCarCurrentlyBooked(registrationNumber)) {
            throw new IllegalStateException(String.format("Car with registration number %s is currently booked.", registrationNumber));
        }

        //  Get available car based on registration number
        Car carToBook = this.carService.getCarByRegistrationNumber(registrationNumber);

        // Car is not booked and available - create a new bookingId
        UUID bookingId = UUID.randomUUID();

        // Create and Save Booking in bookingDAO
        this.bookingDAO.addBooking(
                new Booking(
                        bookingId,
                        user,
                        carToBook,
                        LocalDateTime.now()
                )
        );

        // return the booking id
        return bookingId;
    }

    private boolean isCarCurrentlyBooked(String registrationNumber) {

        // Gets all current bookings
        Booking[] allBookings = getAllBookings();

        // Go through current bookings
        for (Booking booking : allBookings) {

            // Only executes for active (uncancelled) bookings.
            if (!booking.isBookingCancelled()) {

                // Check if the car's registration matches the requested one
                if (booking.getCar().getRegistrationNumber().equals(registrationNumber)) {

                    // The booking is active AND the registration numbers match
                    return true;
                }
            }
        }

        // did not find an active booking matching the requested registration number.
        return false;
    }

    public Booking[] getAllBookings() {

        int allActiveBookingsCount = 0;

        // Booking returned from DAO layer
        Booking[] bookings = this.bookingDAO.getBookingDao();

        // If bookings are null or empty, return empty array
        if (bookings == null || bookings.length == 0){
            return new Booking[0];
        }

        // Count the active bookings
        for (Booking booking : bookings) {
            if (booking != null){
                allActiveBookingsCount++;
            }
        }

        // If no active bookings are found
        if (allActiveBookingsCount == 0) {
            return new Booking[0];
        }

        // Create a new array with the allActiveBookingsCount for size
        Booking[] filteredBookings = new Booking[allActiveBookingsCount];

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the bookings
        for (int i = 0; i < bookings.length; i++) {

            // Use the activeBooking at the current index of the source array
            Booking activeBooking = bookings[i];

            if (activeBooking != null){
                filteredBookings[index++] = activeBooking;
            }

        }

        return filteredBookings;

    }

    public Car[] getUserBookedCarsByUserId(UUID userId) {

        int numberOfUserCarsBooked = 0;

        // Bookings returned from DAO layer
        Booking[] bookings = this.bookingDAO.getBookingDao();

        // If bookings are null or empty, return empty array
        if (bookings == null || bookings.length == 0) {
            return new Car[0];
        }

        // Count the bookings associated to the passed userId
        for (Booking activeBooking : bookings) {

            // If the booking and user exists - check that the activeBooking userId matches the passed userId
            if (activeBooking != null &&
                    activeBooking.getUser() != null &&
                    activeBooking.getUser().getUserId().equals(userId)){
                numberOfUserCarsBooked++;
            }
        }

        // If there are no cars found for the passed userId
        if (numberOfUserCarsBooked == 0) {
            return new Car[0];
        }

        // Create a new array with the numberOfUserCarsBooked for size
        Car[] userCarsBooked = new Car[numberOfUserCarsBooked];

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through bookings
        for (int i = 0; i < bookings.length; i++) {

            // Use the activeBooking at the current index of the source array
            Booking activeBooking = bookings[i];

            // If the active booking and user exists, and the userID matches the passed userId, add the associated car.
            if (activeBooking != null &&
                    activeBooking.getUser() != null &&
                    activeBooking.getUser().getUserId().equals(userId)){
                userCarsBooked[index++] = activeBooking.getCar();

            }
        }

        return userCarsBooked;

    }

}
