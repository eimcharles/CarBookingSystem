
/*
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import Booking.Booking;
import Car.Car;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import User.User;
import Car.Brand;

public class Main {

    public static void main(String[] args) {

        // Unique userId and bookingId for a new booking not cancelled
        User testUser1 = new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer");
        Car testCar1 = new Car("1234", new BigDecimal("89.00"), Brand.BMW, false);
        Booking testBooking1 = new Booking(UUID.fromString("576590ff-57a1-4df3-8430-79980eb42343"), testUser1, testCar1, LocalDateTime.now());
        System.out.println(testBooking1);

        // Unique userId and bookingId for a new booking not cancelled
        User testUser2 = new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Larry", "Eimer");
        Car testCar2 = new Car("1235", new BigDecimal("109.00"), Brand.VOLKSWAGEN, false);
        Booking testBooking2 = new Booking(UUID.fromString("9d818235-ce3b-40e8-b74a-3674985c6bcd"), testUser2, testCar2, LocalDateTime.now());
        System.out.println(testBooking2);

        System.out.println(testBooking1.equals(testBooking2));
        System.out.println(testBooking2.getUserBookingID().equals(testBooking1.getUserBookingID()));
        System.out.println(testBooking1.getBookingTime());

    }
}