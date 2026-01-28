package com.eimc.car;

import com.eimc.exception.CarNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CarRepository {

    private final List<Car> carDAO = new ArrayList<>();

    public CarRepository() {

        // Car available for booking
        addCar(new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC));

    }

    public void addCar(Car car) {

        if (car == null)
            throw new IllegalStateException("Car cannot be null");
        carDAO.add(car);


    }

    public void updateCar(Car carToUpdate) {

        // Car to be canceled registration number
        String registrationNumber = carToUpdate.getRegistrationNumber();

        for (int i = 0; i < this.carDAO.size(); i++) {

            Car currentCar = this.carDAO.get(i);

            // Match the current car with the registration number
            if (currentCar != null && currentCar.getRegistrationNumber().equals(registrationNumber)) {

                // Replace the old object reference with the new
                this.carDAO.set(i, carToUpdate);

                // Stop searching once update is complete
                return;
            }
        }

        // Car not found
        throw new CarNotFoundException(registrationNumber);
    }

    public List<Car> getCars() {
        return new ArrayList<>(this.carDAO);
    }
}
