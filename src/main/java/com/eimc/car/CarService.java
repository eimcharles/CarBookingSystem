package com.eimc.car;

import com.eimc.car.dao.ListCarDAO;
import com.eimc.exception.CarNotFoundException;

import java.util.List;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

public class CarService {

    private final ListCarDAO listCarDAO;

    public CarService(ListCarDAO listCarDAO) {
        this.listCarDAO = listCarDAO;
    }

    public void cancelAssociatedCarToActiveBookingByRegistrationNumber(String registrationNumber){

        /// Fetch Car to be put back in inventory
        Car carToRelease = getCarByRegistrationNumber(registrationNumber);

        /// State change to the Car object - remove the car from booking.
        carToRelease.setCarBooked(false);

        /// Update the state change
        this.listCarDAO.updateCar(carToRelease);

    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        ///  Retrieves car with matching registration number, throws CarNotFoundException if not found
        return getCars().stream()
                .filter(car -> car.getRegistrationNumber().equals(registrationNumber))
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException(registrationNumber));
    }

    public List<Car> getCars() {
        return this.listCarDAO.getCars();
    }

    public List<Car> getAllAvailableCars() {
        /// Retrieves lists of all available (not booked cars)
        return getCars().stream()
                .filter(car -> !car.isCarBooked())
                .toList();
    }

    public List<Car> getAllAvailableCarsByFuelType(FuelType fuelType){
        /// Retrieves lst of all available cars by fuel type
        return getAllAvailableCars()
                .stream()
                .filter(car -> car.getFuelType() == fuelType)
                .toList();
    }

    public List<Car> getAllElectricCars() {
        return getAllAvailableCarsByFuelType(FuelType.ELECTRIC);
    }

    public List<Car> getAllGasolineCars() {
        return getAllAvailableCarsByFuelType(FuelType.GASOLINE);
    }
}
