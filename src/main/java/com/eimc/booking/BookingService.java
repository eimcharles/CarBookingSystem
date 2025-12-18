package com.eimc.booking;

import com.eimc.booking.dao.ArrayBookingDAO;
import com.eimc.car.Car;
import com.eimc.car.CarService;
import com.eimc.exception.BookingNotFoundException;
import com.eimc.exception.CarNotFoundException;
import com.eimc.exception.CarUnavailableException;
import com.eimc.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *      Service class for managing Booking objects.
 *      Contains business logic related to bookings.
 */

public class BookingService {

    private final ArrayBookingDAO arrayBookingDAO;
    private final CarService carService;

    public BookingService(ArrayBookingDAO arrayBookingDAO, CarService carService) {
        this.arrayBookingDAO = arrayBookingDAO;
        this.carService = carService;
    }

    /**
     *      addCarBookingByUserAndRegistrationNumber() creates a booking by car registration
     *      number sets its state to active.
     */

    public UUID addCarBookingByUserAndRegistrationNumber(User user, String registrationNumber) {

        // Check if the car is available for booking by registration number
        Car carToBook = getAvailableCarForBookingByRegistrationNumber(registrationNumber);

        // Car is available - create a new bookingId
        UUID bookingId = UUID.randomUUID();

        // Create and save the new booking
        this.arrayBookingDAO.addBooking(

                new Booking(
                        bookingId,
                        user,
                        carToBook,
                        LocalDateTime.now()

                )
        );

        // Car is booked and associated to booking
        carToBook.setCarBooked(true);

        // Return the booking id
        return bookingId;

    }

    /**
     *      getAvailableCarForBookingByRegistrationNumber() retrieves an available car
     *      (not associated to an active booking) object by its registration number.
     *
     *      @throws CarUnavailableException if the car does not exist in the system.
     */

    public Car getAvailableCarForBookingByRegistrationNumber(String registrationNumber) {

        // Get the car by registration number
        Car carToBook = this.carService.getCarByRegistrationNumber(registrationNumber);

        // Check if car is booked
        if (carToBook.isCarBooked()) {
            throw new CarUnavailableException(registrationNumber);
        }

        return carToBook;
    }

    /**
     *      cancelActiveBookingByBookingId() sets the state of an active booking to inactive.
     *
     *      @throws BookingNotFoundException if the Booking does not exist in the system.
     *
     *      @throws CarNotFoundException if the car does not exist in the system.
     */

    public void cancelActiveBookingByBookingId(UUID validatedBookingId) {

        // Booking with corresponding booking id to cancel
        Booking bookingToCancel = getBookingByBookingId(validatedBookingId);

        // Release the car
        this.carService.cancelAssociatedCarToActiveBookingByRegistrationNumber(bookingToCancel.getCar().getRegistrationNumber());

        // Change the booking state to inactive
        bookingToCancel.cancelBooking();

        // Update the state change
        this.arrayBookingDAO.removeBooking(bookingToCancel);

    }

    /**
     *      getBookingByBookingId() retrieves a booking by booking id.
     */

    public Booking getBookingByBookingId(UUID bookingId){
        return this.arrayBookingDAO.getBookingById(bookingId);
    }

    /**
     *      hasAvailableCarsForBooking() validates available cars for booking in the system.
     */

    public boolean hasAvailableCarsForBooking() {
        return getAllAvailableCars().length > 0;
    }

    /**
     *      hasActiveBookings() validates active bookings present in the system.
     */

    public boolean hasActiveBookings(){
        return getAllActiveBookings().length > 0;
    }

    /**
     *      getBookings() retrieves booking objects from the arrayBookingDAO class, filtering out
     *      any null references that may exist, and returns an array of bookings.
     */

    public Booking[] getBookings() {

        // Get all Bookings from DAO - includes null elements
        Booking[] bookings = this.arrayBookingDAO.getBookings();

        // If bookings are null or empty, return empty array
        if (bookings == null || bookings.length == 0){
            return new Booking[0];
        }

        // Number of nonNullBookings
        int nonNullBookingsCount = getBookingsCount(bookings);

        if (nonNullBookingsCount == 0) {
            return new Booking[0];
        }

        // Create a new array with the nonNullBookingsCount for size
        Booking[] nonNullBookings = new Booking[nonNullBookingsCount];

        // Populates the pre-sized array with nonNullBookings
        populateBookings(bookings, nonNullBookings);

        return nonNullBookings;

    }

    private int getBookingsCount(Booking[] bookings){

        // Count non-null Bookings in DAO array
        int nonNullBookingsCount = 0;
        for (Booking booking : bookings) {
            if (booking != null){
                nonNullBookingsCount++;
            }
        }

        return nonNullBookingsCount;

    }

    private void populateBookings(Booking[] bookings, Booking[] nonNullBookings){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the bookings
        for (int i = 0; i < bookings.length; i++) {

            // Use the nonNullBooking at the current index of the source array
            Booking nonNullBooking = bookings[i];

            if (nonNullBooking != null){
                nonNullBookings[index++] = nonNullBooking;

            }
        }
    }

    /**
     *      getAllActiveBookings() retrieves an array of Bookings that are currently active.
     */

    public Booking[] getAllActiveBookings() {

        // Get all Bookings that are non-null
        Booking[] nonNullBookings = getBookings();

        // If bookings are null or empty, return empty array
        if (nonNullBookings == null || nonNullBookings.length == 0){
            return new Booking[0];
        }

        // Number of activeBookingsCount from nonNullBookings
        int activeBookingsCount = getAllActiveBookingsCount(nonNullBookings);

        if (activeBookingsCount == 0) {
            return new Booking[0];
        }

        // Create a new array with the nonNullBookingsCount for size
        Booking[] activeBookings = new Booking[activeBookingsCount];

        // Populates the pre-sized array with nonNullBookings
        populateAllActiveBookings(nonNullBookings, activeBookings);

        return activeBookings;

    }

    private int getAllActiveBookingsCount(Booking[] bookings){

        // Count bookings that are active
        int activeBookingsCount = 0;
        for (Booking activeBooking : bookings) {

            // Check the booking status
            if (activeBooking != null && activeBooking.isBookingActive()){
                activeBookingsCount++;
            }
        }

        return activeBookingsCount;

    }

    private void populateAllActiveBookings(Booking[] nonNullBookings, Booking[] activeBookings){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the bookings
        for (int i = 0; i < nonNullBookings.length; i++) {

            // Use the nonNullBooking at the current index of the source array
            Booking activeBooking = nonNullBookings[i];

            // Check the booking status
            if (activeBooking != null && activeBooking.isBookingActive()){
                activeBookings[index++] = activeBooking;

            }
        }
    }

    /**
     *      getAllBookedCarsByUserId() retrieves an array of car objects that are
     *      associated to an active booking by user id.
     */

    public Car[] getAllBookedCarsByUserId(UUID userId) {

        // All activeBookings with status active
        Booking[] activeBookings = getAllActiveBookings();

        // If activeBookings are null or empty, return empty array
        if (activeBookings == null || activeBookings.length == 0) {
            return new Car[0];
        }

        // Number of numberOfUserCarsBooked from active bookings
        int numberOfUserCarsBooked = getAllBookedCarsCount(activeBookings,userId);

        if (numberOfUserCarsBooked == 0) {
            return new Car[0];
        }

        // Create a new array with the numberOfUserCarsBooked for size
        Car[] userCarsBooked = new Car[numberOfUserCarsBooked];

        // Populates the pre-sized array with cars associated to the given userId
        populateAllBookedCarsArray(activeBookings, userCarsBooked, userId);

        return userCarsBooked;

    }

    private int getAllBookedCarsCount(Booking[] bookings, UUID userId){

        int numberOfCarsBooked = 0;

        // Count the bookings associated to the passed userId
        for (Booking activeBooking : bookings) {

            // If the booking and user exists - check that the activeBooking userId matches the passed userId
            if (activeBooking != null &&
                    activeBooking.getUser() != null &&
                    activeBooking.getCar() != null &&
                    activeBooking.getUser().getUserId().equals(userId)){

                numberOfCarsBooked++;

            }
        }

        return numberOfCarsBooked;

    }

    private void populateAllBookedCarsArray(Booking[] activeBookings, Car[] userCarsBooked, UUID userId){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through activeBookings
        for (int i = 0; i < activeBookings.length; i++) {

            // Use the activeBooking at the current index of the source array
            Booking activeBooking = activeBookings[i];

            // If the active booking and user exists, and the userID matches the passed userId, add the associated car.
            if (activeBooking != null &&
                    activeBooking.getUser() != null &&
                    activeBooking.getCar() != null &&
                    activeBooking.getUser().getUserId().equals(userId)){

                userCarsBooked[index++] = activeBooking.getCar();

            }
        }
    }

    /**
     *      getAllAvailableCarsForBooking() retrieves an array of car objects that are available
     *      (not associated to an active booking).
     */

    public Car[] getAllAvailableCarsForBooking(Car[] allCars) {

        // No allCars in the system
        if (allCars == null || allCars.length == 0) {
            return new Car[0];
        }

        // Booking that have a status of active
        Booking[] activeBookings = getAllActiveBookings();

        // If activeBookings are null or empty, return passed allCars
        if (activeBookings == null || activeBookings.length == 0) {
            return allCars;
        }

        // Number of allCars that are not associated with any active booking
        int availableCarsCount = getAllAvailableCarsForBookingCount(allCars,activeBookings);

        // If availableCarsCount is 0, return an empty Car array
        if (availableCarsCount == 0) {
            return new Car[0];
        }

        // Create a new array with the allActiveBookingsCount for size
        Car[] availableCars = new Car[availableCarsCount];

        // Populates the pre-sized array with availableCars that are NOT booked.
        populateAvailableCarsForBooking(allCars, activeBookings, availableCars);

        return availableCars;

    }

    private int getAllAvailableCarsForBookingCount(Car[] availableCars, Booking[] activeBookings){

        int availableCarsCount = 0;

        // Check if a car is associated to any booking
        for (Car car : availableCars) {

            // Assume the car is not booked initially
            boolean isCarAssociatedToBooking = false;

            // Check the current car against every active booking.
            for (Booking activeBooking : activeBookings) {

                // A car is UNAVAILABLE (booked) if:
                // a) It is the car associated with the booking
                // b) The booking is NOT cancelled (i.e., it is currently active)
                if (activeBooking != null &&
                        activeBooking.getCar() != null &&
                        activeBooking.getCar().equals(car)) {

                    // The car is booked
                    isCarAssociatedToBooking = true;

                    // Stop checking this booking against other cars
                    break;
                }
            }

            // the car was NOT booked, it is available.
            if (!isCarAssociatedToBooking) {
                availableCarsCount++;
            }
        }

        return availableCarsCount;

    }

    private void populateAvailableCarsForBooking(Car[] allCars, Booking[] activeBookings, Car[] availableCars){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Check if a car is associated to any booking
        for (Car car : allCars) {

            // Assume the car is not booked initially
            boolean isCarAssociatedToBooking = false;

            // Check the current car against every existing booking.
            for (Booking activeBooking : activeBookings) {

                // A car is UNAVAILABLE (booked) if:
                // a) It is the car associated with the booking
                // b) The booking is NOT cancelled (i.e., it is currently active)
                if (activeBooking != null &&
                        activeBooking.getCar() != null &&
                        activeBooking.getCar().equals(car)) {

                    // The car is booked
                    isCarAssociatedToBooking = true;

                    // Stop checking this car against other activeBooking
                    break;

                }
            }

            // the car was NOT booked, it is available.
            if (!isCarAssociatedToBooking) {

                // Add the car to the availableCars
                availableCars[index++] = car;

            }
        }
    }

    public Car[] getAllAvailableCars(){
        return getAllAvailableCarsForBooking(carService.getAllAvailableCars());
    }

    public Car[] getAllAvailableGasCars(){
        return getAllAvailableCarsForBooking(carService.getAllGasolineCars());
    }

    public Car[] getAllAvailableElectricCars(){
        return getAllAvailableCarsForBooking(carService.getAllElectricCars());
    }
}
