
/**
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import Car.Car;
import Car.CarDAO;
import Car.CarService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);

        // Getting a car by registration number
        Car testCar = carService.getCarsByIdFromDAO("123_1");
        System.out.println(testCar);

        // Getting the list of cars in DAO
        System.out.println(Arrays.toString(carService.getCarDAO()));

    }
}