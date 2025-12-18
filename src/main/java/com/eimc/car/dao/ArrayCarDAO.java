package com.eimc.car.dao;

import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.exception.CarNotFoundException;
import com.eimc.user.User;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *      ArrayCarDAO is a Data Access Object (DAO)
 *      class for managing Car objects,
 *      it implements the CarDAO contract.
 *
 *      This implementation stores and manages cars using arrays.
 */

public class ArrayCarDAO implements CarDAO {

    private final Car[] carDAO;
    private int numberOfCars = 0;
    private static final int MAX_CAPACITY_OF_CARS = 4;

    public ArrayCarDAO() {

        this.carDAO = new Car[MAX_CAPACITY_OF_CARS];

        // Available for booking
        addCar(new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC));
        addCar(new Car("123_2", new BigDecimal("79.00"), Brand.BMW, FuelType.GASOLINE));
        addCar(new Car("123_3", new BigDecimal("69.00"), Brand.VOLKSWAGEN, FuelType.ELECTRIC));

        // Car booked in system
        addCar(new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC));

    }

    @Override
    public Car[] getCars() {
        // Copy of Car objects returned from carDAO
        return Arrays.copyOf(this.carDAO, this.carDAO.length);
    }

    /**
     *      addCar() adds a new car to carDAO
     *      array.
     *
     *      The car is stored at the next available
     *      index in the internal array, and the index
     *      is then incremented.
     *
     *      @throws IllegalStateException if the internal
     *      storage array is full and no more cars can
     *      be added.
     */

    @Override
    public void addCar(Car car) {

        if (this.numberOfCars >= this.carDAO.length) {
            throw new IllegalStateException(String.format("No more available space to add cars"));
        }

        this.carDAO[this.numberOfCars] = car;
        this.numberOfCars++;
    }

    /**
     *      updateCar() ensures that the carDAO array contains
     *      the most up-to-date reference to the car object,
     *      persisting any recent changes made in the Service layer.
     *
     *      (e.g., setCarBooked(false)).
     */

    @Override
    public void updateCar(Car carToUpdate) {

        // Car to be cancelled registration number
        String registrationNumber = carToUpdate.getRegistrationNumber();

        for (int i = 0; i < this.numberOfCars; i++) {

            Car currentCar = this.carDAO[i];

            // Match the current car with the registration number
            if (currentCar != null && currentCar.getRegistrationNumber().equals(registrationNumber)) {

                // Replace the old object reference with the new
                this.carDAO[i] = carToUpdate;

                // Car found and updated
                return;
            }
        }

        // Car not found
        throw new CarNotFoundException(registrationNumber);
    }

}
