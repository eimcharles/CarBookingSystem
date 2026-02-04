package com.eimc.car.service;

import com.eimc.car.model.Car;
import com.eimc.car.exception.CarNotFoundException;
import com.eimc.car.repository.CarRepository;
import com.eimc.car.model.enums.FuelType;

import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 *
 *      TODO fix Car Service business logic for Spring Boot
 */

public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void cancelAssociatedCarToActiveBookingByRegistrationNumber(UUID registrationNumber){

        /// Fetch Car to be put back in inventory
        Car carToRelease = getCarByRegistrationNumber(registrationNumber);

        /// State change to the Car object - remove the car from booking.
        carToRelease.setCarBooked(false);

        /// Update the state change
        this.carRepository.updateCar(carToRelease);

    }

    public Car getCarByRegistrationNumber(UUID registrationNumber) {
        ///  Retrieves car with matching registration number, throws CarNotFoundException if not found
        return getCars().stream()
                .filter(car -> car.getCarId().equals(registrationNumber))
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException(registrationNumber));
    }

    public List<Car> getCars() {
        return this.carRepository.getCars();
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

    public List<Car> getElectricCars() {
        return getAllAvailableCarsByFuelType(FuelType.ELECTRIC);
    }

    public List<Car> getGasolineCars() {
        return getAllAvailableCarsByFuelType(FuelType.GASOLINE);
    }

}
