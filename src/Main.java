
/**
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import Car.Car;
import Car.CarDAO;
import Car.CarService;
import User.User;
import User.UserDAO;
import User.UserService;

import java.util.Arrays;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);

        // Getting a car by registration number
        Car testCar = carService.getCarsByIdFromDAO("1231");
        System.out.println(testCar);

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);

        User testUser1 = userService.getUsersByIdFromDAO(UUID.fromString("1234"));
        System.out.println(testUser1);


    }
}