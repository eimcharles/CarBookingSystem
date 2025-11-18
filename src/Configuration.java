import Booking.BookingDAO;
import Booking.BookingService;
import Car.CarDAO;
import Car.CarService;
import User.UserDAO;
import User.UserService;

public class Configuration {

    // Final Attributes to hold the instantiated and wired services
    private final CarService carService;
    private final UserService userService;
    private final BookingService bookingService;

    public Configuration() {

        CarDAO carDAO = new CarDAO();
        UserDAO userDAO = new UserDAO();
        BookingDAO bookingDAO = new BookingDAO();

        this.carService = new CarService(carDAO);
        this.userService = new UserService(userDAO);
        this.bookingService = new BookingService(bookingDAO);
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
