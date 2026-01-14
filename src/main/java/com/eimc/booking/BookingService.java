package com.eimc.booking;

import com.eimc.booking.dao.ListBookingDAO;
import com.eimc.car.Car;
import com.eimc.car.CarService;
import com.eimc.exception.BookingNotFoundException;
import com.eimc.exception.CarNotFoundException;
import com.eimc.exception.CarUnavailableException;
import com.eimc.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing Booking objects.
 *      Contains business logic related to bookings.
 */

public class BookingService {

    private final ListBookingDAO listBookingDAO;
    private final CarService carService;

    public BookingService(ListBookingDAO listBookingDAO, CarService carService) {
        this.listBookingDAO = listBookingDAO;
        this.carService = carService;
    }

    /**
     *      addCarBookingByUserAndRegistrationNumber() creates a booking by car registration
     *      number sets its state to active.
     */

    public UUID addCarBookingByUserAndRegistrationNumber(User user, String registrationNumber) {

        /// Check if the car is available for booking by registration number
        Car carToBook = getAvailableCarForBookingByRegistrationNumber(registrationNumber);

        ///  Car is available - create a new bookingId
        UUID bookingId = UUID.randomUUID();

        /// Create and save the new booking
        this.listBookingDAO.addBooking(

                new Booking(
                        bookingId,
                        user,
                        carToBook,
                        LocalDateTime.now()

                )
        );

        ///  Car is booked and associated to booking
        carToBook.setCarBooked(true);

        ///  Return the booking id
        return bookingId;

    }

    /**
     *      getAvailableCarForBookingByRegistrationNumber() retrieves an available car
     *      (not associated to an active booking) object by its registration number.
     *
     *      @throws CarUnavailableException if the car does not exist in the system.
     */

    public Car getAvailableCarForBookingByRegistrationNumber(String registrationNumber) {

        /// Get the car by registration number
        Car carToBook = this.carService.getCarByRegistrationNumber(registrationNumber);

        ///  Check if car is booked
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

        /// Booking with corresponding booking id to cancel
        Booking bookingToCancel = getBookingByBookingId(validatedBookingId);

        /// Release the car
        this.carService.cancelAssociatedCarToActiveBookingByRegistrationNumber(bookingToCancel.getCar().getRegistrationNumber());

        ///  Change the booking state to inactive
        bookingToCancel.cancelBooking();

        ///  Update the state change
        this.listBookingDAO.removeBooking(bookingToCancel);

    }

    public Booking getBookingByBookingId(UUID bookingId){
        return this.listBookingDAO.getBookingById(bookingId);
    }

    public boolean hasAvailableCarsForBooking() {
        return !getAllAvailableCars().isEmpty();
    }

    public boolean hasActiveBookings(){
        return !getAllActiveBookings().isEmpty();
    }

    public List<Booking> getBookings() {
        return this.listBookingDAO.getBookings();
    }

    public List<Booking> getAllActiveBookings() {

        ///  Retrieves all booking with status active
        return getBookings()
                .stream()
                .filter(Booking::isBookingActive)
                .toList();
    }

    public List<Car> getAllBookedCarsByUserId(UUID userId) {

        /// All activeBookings with status active
        List<Booking> activeBookings = getAllActiveBookings();

        /// Retrieves all booked cars for a matching UUID  user id
        return activeBookings.stream()
                .filter(booking -> booking != null &&
                        booking.getUser() != null &&
                        booking.getCar() != null &&
                        booking.getUser().getUserId().equals(userId))
                        .map(Booking::getCar)
                .toList();
    }

    public List<Car> getAllAvailableCarsForBooking(List<Car> allCars) {

        if (allCars.isEmpty()) {
            return new ArrayList<>();
        }

        /// Bookings that have a status of active
        List<Booking> activeBookings = getAllActiveBookings();

        /// Go through all the active bookings and get all associated booked cars
        List<Car> bookedCars = activeBookings.stream()
                .map(Booking::getCar)
                .toList();

        /// Filter all cars by excluding all booked cars
        return allCars.stream()
                .filter(car -> !bookedCars.contains(car))
                .toList();
    }

    public List<Car> getAllAvailableCars(){
        return getAllAvailableCarsForBooking(carService.getAllAvailableCars());
    }

    public List<Car> getAllAvailableGasCars(){
        return getAllAvailableCarsForBooking(carService.getAllGasolineCars());
    }

    public List<Car> getAllAvailableElectricCars(){
        return getAllAvailableCarsForBooking(carService.getAllElectricCars());
    }
}
