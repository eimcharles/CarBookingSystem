package com.eimc.car.dao;

import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.exception.CarNotFoundException;

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

    public ArrayCarDAO() {
        this.carDAO = new Car[]{

                new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC),
                new Car("123_2", new BigDecimal("79.00"), Brand.BMW, FuelType.GASOLINE),
                new Car("123_3", new BigDecimal("69.00"), Brand.VOLKSWAGEN, FuelType.ELECTRIC),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                new Car("123_5", new BigDecimal("59.00"), Brand.HONDA, FuelType.GASOLINE),

        };
    }

    @Override
    public Car[] getCars() {
        // Copy of Car objects returned from carDAO
        return Arrays.copyOf(this.carDAO, this.carDAO.length);
    }


    /**
     *      Transactional commit:
     *
     *      updateCar() ensures that the carDAO array slot at position i contains
     *      the most up-to-date reference to the car object, persisting
     *      any recent changes made in the Service layer.
     *
     * (        e.g., setBooked(false)).
     *
     */

    @Override
    public void updateCar(Car carToUpdate) {

        // Car to be cancelled registration number
        String registrationNumber = carToUpdate.getRegistrationNumber();

        boolean carFound = false;

        for (int i = 0; i < this.carDAO.length; i++) {

            Car currentCar = this.carDAO[i];

            // Match the car to be cancelled with the registration number
            if (currentCar != null && currentCar.getRegistrationNumber().equals(registrationNumber)) {

                // Replace the old object reference with the new,
                this.carDAO[i] = carToUpdate;

                // car found
                carFound = true;

                break;
            }
        }

        if (!carFound) {
            throw new CarNotFoundException(registrationNumber);
        }

    }

}
