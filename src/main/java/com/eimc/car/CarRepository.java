package com.eimc.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarRepository {

    private final List<Car> carDAO = new ArrayList<>();

    public CarRepository() {

        // Car available for booking
        addCar(new Car(UUID.fromString("a6079ab3-06ff-4983-af48-78b3ee4e2962"), Model.TIGUAN, new BigDecimal("49.00")));

    }

    public void addCar(Car car) {

        if (car == null)
            throw new IllegalStateException("Car cannot be null");
        carDAO.add(car);


    }

    public void updateCar(Car carToUpdate) {

        // Car to be canceled registration number
        UUID registrationNumber = carToUpdate.getCarId();

        for (int i = 0; i < this.carDAO.size(); i++) {

            Car currentCar = this.carDAO.get(i);

            // Match the current car with the registration number
            if (currentCar != null && currentCar.getCarId().equals(registrationNumber)) {

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
