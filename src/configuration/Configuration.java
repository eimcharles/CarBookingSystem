package configuration;

import booking.BookingDAO;
import booking.BookingService;
import car.CarDAO;
import car.CarService;
import user.UserDAO;
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

        CarDAO carDAO = new CarDAO();
        UserDAO userDAO = new UserDAO();
        BookingDAO bookingDAO = new BookingDAO();

        this.carService = new CarService(carDAO);
        this.userService = new UserService(userDAO);
        this.bookingService = new BookingService(bookingDAO, getCarService());
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
