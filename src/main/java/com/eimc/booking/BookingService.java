package com.eimc.booking;

import com.eimc.car.model.Car;
import com.eimc.car.service.CarService;
import com.eimc.car.exception.CarNotFoundException;
import com.eimc.car.exception.CarUnavailableException;
import com.eimc.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing Booking objects.
 *      Contains business logic related to bookings.
 *
 *      TODO fix Booking Service business logic for Spring Boot
 *
 */

public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarService carService;

    public BookingService(BookingRepository bookingRepository, CarService carService) {
        this.bookingRepository = bookingRepository;
        this.carService = carService;
    }

    /**
     *      addCarBookingByUserAndRegistrationNumber() creates a booking by car registration
     *      number sets its state to active.
     */

    public UUID addCarBookingByUserAndRegistrationNumber(User user, UUID registrationNumber) {

        /// Check if the car is available for booking by registration number
        Car carToBook = getAvailableCarForBookingByRegistrationNumber(registrationNumber);

        ///  Car is available - create a new bookingId
        UUID bookingId = UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef");

        /// Create and save the new booking
        this.bookingRepository.addBooking(

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

    public Car getAvailableCarForBookingByRegistrationNumber(UUID registrationNumber) {

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
        this.carService.cancelAssociatedCarToActiveBookingByRegistrationNumber(bookingToCancel.getCar().getCarId());

        ///  Change the booking state to inactive
        bookingToCancel.cancelBooking();

        ///  Update the state change
        this.bookingRepository.removeBooking(bookingToCancel);

    }

    public Booking getBookingByBookingId(UUID bookingId){
        return this.bookingRepository.getBookingById(bookingId);
    }

    public List<Booking> getBookings() {
        return this.bookingRepository.getBookings();
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
        return getAllAvailableCarsForBooking(carService.getGasolineCars());
    }

    public List<Car> getAllAvailableElectricCars(){
        return getAllAvailableCarsForBooking(carService.getElectricCars());
    }
}
