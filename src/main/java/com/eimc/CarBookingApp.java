package com.eimc;

import com.eimc.booking.BookingService;
import com.eimc.booking.BookingRepository;
import com.eimc.car.Car;
import com.eimc.car.CarService;
import com.eimc.car.CarRepository;
import com.eimc.booking.BookingNotFoundException;
import com.eimc.car.CarNotFoundException;
import com.eimc.car.CarUnavailableException;
import com.eimc.user.exception.UserNotFoundException;
import com.eimc.user.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

/**
 *      CarBookingApp is the entry point to start the application.
 * */

@SpringBootApplication
public class CarBookingApp {

    public static void main(String[] args) {

        SpringApplication.run(CarBookingApp.class, args);

        ///  Dependency injection
        CarRepository carRepository = new CarRepository();
        BookingRepository bookingRepository = new BookingRepository();

        CarService carService = new CarService(carRepository);
        BookingService bookingService = new BookingService(bookingRepository, carService);

        ///  Make a car booking for Jerry
        System.out.println();
        makeACarBookingByUser(bookingService);
        System.out.println();

        ///  Display the car that Jerry booked
        displayCarsBookedByUserByUserId(bookingService);
        System.out.println();

        ///  Cancel the booking for Jerry
        cancelCarBookingByBookingId(bookingService);
        System.out.println();

        ///  Display all available cars after the booking is canceled
        carService.getAllAvailableCars().forEach(System.out::println);
        System.out.println();

    }

    public static void makeACarBookingByUser(BookingService bookingService) {

        try {

            User user = new User(
                    "Jerry",
                    "LeBlond",
                    "jerry@email.com",
                    "securePass123",
                    "514-555-5555"
            );

            user.setUserId(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"));

            // Attempt to create the booking by associating the user and car registration
            UUID userBookingID = bookingService.addCarBookingByUserAndRegistrationNumber(user, UUID.fromString("a6079ab3-06ff-4983-af48-78b3ee4e2962"));
            // Display booking success message
            System.out.println("Successfully created a car booking for " + user.getFirstName() + " with booking id: " + userBookingID + ".");

        }  catch (UserNotFoundException e) {
            // User does not found
            System.out.println(e.getMessage());
        } catch (CarNotFoundException e) {
            // Car does not found
            System.out.println(e.getMessage());
        } catch (CarUnavailableException e) {
            // Car is unavailable (booked)
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Booking attempt failed - Please try again.");
        }
    }

    public static void cancelCarBookingByBookingId(BookingService bookingService) {

        try {
            bookingService.cancelActiveBookingByBookingId(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"));
            System.out.println("Successfully cancelled the booking with booking id: 8e397f1e-e7a4-4c39-8331-968a9ab3faef.");
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }  catch (Exception e) {
            System.out.println("No bookings currently registered in the system");
        }
    }

    public static void displayCarsBookedByUserByUserId(BookingService bookingService) {
        List<Car> bookedCars = bookingService.getAllBookedCarsByUserId(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"));
        bookedCars.forEach(System.out::println);
    }

}
