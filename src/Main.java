
/*
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import Car.Car;

import java.math.BigDecimal;
import Car.Brand;

public class Main {

    public static void main(String[] args) {

        Car testCar1 = new Car("1234", new BigDecimal("89.00"), Brand.BMW, false);
        Car testCar2 = new Car("1234", new BigDecimal("89.00"), Brand.BMW, true);
        Car testCar3 = new Car("1235", new BigDecimal("79.00"), Brand.BMW, true);

        System.out.println(testCar1);
        System.out.println(testCar2);
        System.out.println(testCar1.equals(testCar2));
        System.out.println(testCar1.equals(testCar3));

    }
}