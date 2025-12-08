package booking;

import booking.dao.ArrayBookingDAO;
import car.Car;
import car.CarService;
import exception.BookingNotActiveException;
import exception.BookingNotFoundException;
import exception.CarUnavailableException;
import user.User;

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
     *
     *
     */

    public UUID addCarBookingByUserAndRegistrationNumber(User user, String registrationNumber) {

        // Check if the car is currently tied to any active booking by registration number
        Car carToBook = getAvailableCarForBookingByRegistrationNumber(registrationNumber);

        // Car is not booked and available - create a new bookingId
        UUID bookingId = UUID.randomUUID();

        // Create and Save Booking in bookingDAO
        this.arrayBookingDAO.addBooking(

                new Booking(
                        bookingId,
                        user,
                        carToBook,
                        LocalDateTime.now()

                )
        );

        // car is booked an unavailable
        carToBook.setBooked(true);

        // return the booking id
        return bookingId;

    }

    /**
     *
     *
     */

    public Car getAvailableCarForBookingByRegistrationNumber(String registrationNumber) {

        // Get the car by registration number
        Car carToBook = this.carService.getCarByRegistrationNumber(registrationNumber);

        // Check if car is booked
        if (carToBook.isBooked()) {

            throw new CarUnavailableException(registrationNumber);

        }

        return carToBook;
    }

    /**
     *
     *
     */

    public void cancelActiveBookingByBookingId(UUID validatedBookingId) {

        // Booking with corresponding booking id
        Booking bookingToCancel = getBookingByBookingId(validatedBookingId);

        // Check if the booking exists
        if (bookingToCancel == null) {
            throw new BookingNotFoundException(validatedBookingId);
        }

        // Activity Check - prevent re-cancellation
        if (bookingToCancel.isBookingCancelled()) {
            throw new BookingNotActiveException(validatedBookingId);
        }

        // Cancel booking
        bookingToCancel.cancelBooking();

        // Holds booking cancellation status
        boolean isCancelledAndUpdated = this.arrayBookingDAO.updateBooking(bookingToCancel);

        // booking couldn't be cancelled
        if (!isCancelledAndUpdated) {
            throw new BookingNotFoundException(validatedBookingId);
        }

        //Release the Asset (Car)
        this.carService.cancelAssociatedCarByRegistrationNumber(bookingToCancel.getCar().getRegistrationNumber());

    }

    /**
     *
     *
     */

    public Booking getBookingByBookingId(UUID bookingId){
        return this.arrayBookingDAO.getBookingById(bookingId);
    }

    /**
     *
     *
     */

    public boolean hasActiveBookings(){
        return getAllActiveBookings().length > 0;
    }

    /**
     *
     *
     */

    public Car[] getAllAvailableCars(){
        return getAllAvailableCarsForBooking(carService.getAllAvailableCars());
    }

    /**
     *
     *
     */

    public Car[] getAllAvailableGasCars(){
        return getAllAvailableCarsForBooking(carService.getAllGasolineCars());
    }

    /**
     *
     *
     */

    public Car[] getAllAvailableElectricCars(){
        return getAllAvailableCarsForBooking(carService.getAllElectricCars());
    }

    /**
     *
     *
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
        populateAvailableCars(allCars, activeBookings, availableCars);

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

    private void populateAvailableCars(Car[] allCars, Booking[] activeBookings, Car[] availableCars){

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

    /**
     *
     *
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
     *
     *
     */

    public Booking[] getAllActiveBookings() {

        // Get all Bookings that are non-null
        Booking[] nonNullBookings = getBookings();

        // If bookings are null or empty, return empty array
        if (nonNullBookings == null || nonNullBookings.length == 0){
            return new Booking[0];
        }

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
            if (activeBooking != null && !activeBooking.isBookingCancelled()){
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
            if (activeBooking != null && !activeBooking.isBookingCancelled()){
                activeBookings[index++] = activeBooking;

            }
        }
    }

    /**
     *
     *
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
}
