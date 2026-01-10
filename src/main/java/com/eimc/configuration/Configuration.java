package com.eimc.configuration;

import com.eimc.booking.dao.ArrayBookingDAO;
import com.eimc.booking.BookingService;
import com.eimc.car.dao.ListCarDAO;
import com.eimc.car.CarService;
import com.eimc.user.dao.ListUserDAO;
import com.eimc.user.UserService;

/**
 *      The Configuration class is a factory class
 *      responsible for initializing and wiring all
 *      application services (Dependency Injection).
 *
 *      It instantiates the Data Access Objects (DAOs)
 *      and injects them into their corresponding
 *      Service layer components, providing a central
 *      source of configured services to the application entry point.
 * */

public class Configuration {


    private final CarService carService;
    private final UserService userService;
    private final BookingService bookingService;

    public Configuration() {

        ListCarDAO listCarDAO = new ListCarDAO();
        ListUserDAO listUserDAO = new ListUserDAO();
        ArrayBookingDAO arrayBookingDAO = new ArrayBookingDAO();

        this.carService = new CarService(listCarDAO);
        this.userService = new UserService(listUserDAO);
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
