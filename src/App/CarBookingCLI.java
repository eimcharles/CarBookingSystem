package App;

import Booking.Booking;
import Booking.BookingService;
import Car.Car;
import User.User;
import User.UserService;

/**
 *      Driver class for Car Booking System CLI
 *
 *      TODO: Implement Driver Class
 * */

public class CarBookingCLI {


    public static void makeACarBooking(BookingService bookingService){

        displayAllAvailableCars(bookingService);
        ///  TODO implement logic

    }


    public static void displayUserBookedCars(UserService userService){

        displayAllUsers(userService);
        ///  TODO implement logic

    }

    /**
     *      Retrieves all currently available
     *      cars that run on gasoline from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the gas car is
     *      not tied to any active booking.
     *
     *       If no gas cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableGasCars(BookingService bookingService) {

        Car[] availableGasCars = bookingService.getAvailableGasCars();

        if (availableGasCars == null || availableGasCars.length == 0) {
            System.out.println("❌ No gas cars currently available for booking");
            return;
        }

        for (Car availableGasCar : availableGasCars) {
            System.out.println(availableGasCar);
        }
    }

    /**
     *      Retrieves all currently available
     *      cars that run on electricity from the
     *      BookingService and prints them to the console.
     *
     *      Availability means the electric car is
     *       not tied to any active booking.
     *
     *      If no electric cars are available, a
     *      corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the filtered availability data.
     */

    public static void displayAllAvailableElectricCars(BookingService bookingService) {

        Car[] availableElectricCars = bookingService.getAvailableElectricCars();

        if (availableElectricCars == null || availableElectricCars.length == 0) {
            System.out.println("❌ No electric cars currently available for booking");
            return;
        }

        for (Car availableEelectricCar : availableElectricCars) {
            System.out.println(availableEelectricCar);
        }
    }

    /**
     *          Retrieves all currently available
     *          cars from the BookingService and
     *          prints them to the console.
     *
     *          Availability means the car is
     *          not tied to any active booking.
     *
     *          If no cars are available, a
     *          corresponding message is displayed.
     *
     *          @param bookingService
     *
     *          The BookingService instance
     *          used to fetch the availability data.
     *
     */

    public static void displayAllAvailableCars(BookingService bookingService) {

        Car[] availableCars = bookingService.getAllAvailableCars();

        if (availableCars == null || availableCars.length == 0) {
            System.out.println("❌ No cars currently available for booking");
            return;
        }

        for (Car availableCar : availableCars) {
            System.out.println(availableCar);
        }
    }

    /**
     *      Retrieves all active
     *      bookings from the BookingService
     *      and prints them to the console.
     *
     *      If no active bookings are found,
     *      a corresponding message is displayed.
     *
     *      @param bookingService
     *
     *      The BookingService instance
     *      used to fetch the data.
     *
     */

    public static void displayAllBooking(BookingService bookingService){

        Booking[] allBookings = bookingService.getAllBookings();

        if (allBookings == null || allBookings.length == 0){
            System.out.println("❌ No bookings currently registered in the system");
            return;
        }

        for (Booking allBooking : allBookings) {
            System.out.println(allBooking);
        }
    }

    /**
     *          Retrieves all registered
     *          users from the UserService
     *          and prints them to the console.
     *
     *          If no users are found,
     *          a corresponding message is displayed.
     *
     *          @param userService
     *
     *          The UserService instance
     *          used to fetch the data.
     *          
     */

    public static void displayAllUsers(UserService userService){

        User[] users = userService.getAllUsers();

        if (users == null || users.length == 0){
            System.out.println("❌ No users currently registered in the system");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }
}
