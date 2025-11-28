package configuration;

import booking.ArrayBookingDAO;
import booking.BookingService;
import car.ArrayCarDAO;
import car.CarService;
import user.ArrayUserDAO;
import user.UserService;

/**
 *      The Configuration class is a factory class
 *      responsible for initializing and wiring all
 *      application services (Dependency Injection).
 *
 *      It instantiates the Data Access Objects (DAOs)
 *      and injects them into their corresponding
 *      Service layer components, providing a central
 *      source of configured services to the application entry point.
 *
 * */

public class Configuration {


    private final CarService carService;
    private final UserService userService;
    private final BookingService bookingService;

    public Configuration() {

        ArrayCarDAO arrayCarDAO = new ArrayCarDAO();
        ArrayUserDAO arrayUserDAO = new ArrayUserDAO();
        ArrayBookingDAO arrayBookingDAO = new ArrayBookingDAO();

        this.carService = new CarService(arrayCarDAO);
        this.userService = new UserService(arrayUserDAO);
        this.bookingService = new BookingService(arrayBookingDAO, getCarService());
    }

    public CarService getCarService() {
        return carService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }
}
