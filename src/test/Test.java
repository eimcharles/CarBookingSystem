package test;

import configuration.Configuration;

import booking.BookingService;
import booking.dao.ArrayBookingDAO;

import car.CarService;
import car.dao.ArrayCarDAO;

import java.util.Scanner;

/**
 *      Test class for Car Booking System CLI
 *
 *      Serves as a Testing environment to
 *      ensure proper implementation and
 *      output.
 * */

public class Test {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        Scanner scanner = new Scanner(System.in);

        ArrayCarDAO arrayCarDAO = new ArrayCarDAO();
        CarService carService = new CarService(arrayCarDAO);

        ArrayBookingDAO arrayBookingDAO = new ArrayBookingDAO();
        BookingService bookingService = new BookingService(arrayBookingDAO, carService);

    }
}