package booking;

import car.Car;
import car.CarService;
import user.User;

import java.time.LocalDateTime;
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

    public boolean isCarCurrentlyBooked(String registrationNumber) {

        // Get bookings
        Booking[] bookings = getAllBookings();

        // Go through bookings
        for (Booking booking : bookings) {

            // Make sure the booking is still active
            if (!booking.isBookingCancelled()) {

                // Check if the car's registration matches the requested one
                if (booking.getCar().getRegistrationNumber().equals(registrationNumber)) {

                    // Match found: the car is currently booked
                    return true;

                }
            }
        }

        // No Match found: the car is not currently booked
        return false;

    }

    public Booking[] getAllBookings() {

        // Booking returned from DAO layer
        Booking[] bookings = this.bookingDAO.getBookingDao();

        // If bookings are null or empty, return empty array
        if (bookings == null || bookings.length == 0){
            return new Booking[0];
        }

        // Number of active bookings
        int allActiveBookingsCount = countActiveBookings(bookings);

        // If no active bookings are found
        if (allActiveBookingsCount == 0) {
            return new Booking[0];
        }

        // Create a new array with the allActiveBookingsCount for size
        Booking[] filteredBookings = new Booking[allActiveBookingsCount];

        // Populates the pre-sized array with active bookings
        populateBookingsArray(bookings, filteredBookings);

        return filteredBookings;

    }

    public Car[] getUserBookedCarsByUserId(UUID userId) {

        // Bookings returned from DAO layer
        Booking[] bookings = this.bookingDAO.getBookingDao();

        // If bookings are null or empty, return empty array
        if (bookings == null || bookings.length == 0) {
            return new Car[0];
        }

        // Number of user booked cars
        int numberOfUserCarsBooked = countUserBookedCars(bookings,userId);

        // If there are no cars found for the passed userId
        if (numberOfUserCarsBooked == 0) {
            return new Car[0];
        }

        // Create a new array with the numberOfUserCarsBooked for size
        Car[] userCarsBooked = new Car[numberOfUserCarsBooked];

        // Populates the pre-sized array with cars associated to the given userId
        populateUserBookedCarsArray(bookings, userId, userCarsBooked);

        return userCarsBooked;

    }

    public Car[] getAllAvailableCars(){
        return getAvailableCars(carService.getAllCars());
    }

    public Car[] getAvailableGasCars(){
        return getAvailableCars(carService.getGasolineCars());
    }

    public Car[] getAvailableElectricCars(){
        return getAvailableCars(carService.getElectricCars());
    }

    public Car[] getAvailableCars(Car[] allCars) {

        // No cars in the system
        if (allCars == null || allCars.length == 0) {
            return new Car[0];
        }

        // Booking returned from DAO layer
        Booking[] bookings = bookingDAO.getBookingDao();

        // If bookings are null or empty, return allCars
        if (bookings == null || bookings.length == 0) {
            return allCars;
        }

        // Number of cars that are not associated with any active booking
        int availableCarsCount = countAvailableCars(allCars,bookings);

        // If availableCarsCount is 0, return an empty Car array
        if (availableCarsCount == 0) {
            return new Car[0];
        }

        // Create a new array with the allActiveBookingsCount for size
        Car[] availableCars = new Car[availableCarsCount];

        // Populates the pre-sized array with cars that are NOT booked.
        populateAvailableCarsArray(allCars, bookings, availableCars);

        return availableCars;

    }

    private int countAvailableCars(Car[] allCars, Booking[] bookings){

        int availableCarsCount = 0;

        // Check if a car is associated to any booking
        for (Car car : allCars) {

            // Assume the car is not booked initially
            boolean isCarAssociatedToBooking = false;

            // Check the current car against every existing booking.
            for (Booking booking : bookings) {

                // Ensure the booking and its car exist, then check for a registration match.
                if (booking != null &&
                        booking.getCar() != null &&
                        booking.getCar().equals(car)) {

                    // The car is booked
                    isCarAssociatedToBooking = true;

                    // Stop checking this car against other bookings
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

    private void populateAvailableCarsArray(Car[] allCars, Booking[] bookings, Car[] availableCars){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Check if a car is associated to any booking
        for (Car car : allCars) {

            // Assume the car is not booked initially
            boolean isCarAssociatedToBooking = false;

            // Check the current car against every existing booking.
            for (Booking booking : bookings) {

                // Ensure the booking and its car exist, then check for a registration match.
                if (booking != null &&
                        booking.getCar() != null &&
                        booking.getCar().equals(car)) {

                    // The car is booked
                    isCarAssociatedToBooking = true;

                    // Stop checking this car against other bookings
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

    private int countActiveBookings(Booking[] bookings){

        int allActiveBookingsCount = 0;

        // Count the active bookings
        for (Booking booking : bookings) {
            if (booking != null){
                allActiveBookingsCount++;
            }
        }

        return allActiveBookingsCount;

    }

    private void populateBookingsArray(Booking[] bookings, Booking[] filteredBookings){

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
    }

    private int countUserBookedCars(Booking[] bookings, UUID userId){

        int numberOfUserCarsBooked = 0;

        // Count the bookings associated to the passed userId
        for (Booking activeBooking : bookings) {

            // If the booking and user exists - check that the activeBooking userId matches the passed userId
            if (activeBooking != null &&
                    activeBooking.getUser() != null &&
                    activeBooking.getUser().getUserId().equals(userId)){

                numberOfUserCarsBooked++;

            }
        }

        return numberOfUserCarsBooked;

    }

    private void populateUserBookedCarsArray(Booking[] bookings, UUID userId, Car[] userCarsBooked){

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
    }

}
