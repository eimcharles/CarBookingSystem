package com.eimc.car.service;

import com.eimc.car.model.Car;
import com.eimc.car.exception.CarNotFoundException;
import com.eimc.car.repository.CarRepository;
import com.eimc.car.model.enums.FuelType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        ///  TODO Add car validation
        return carRepository.save(car);
    }

    public List<Car> getCars() {
        return this.carRepository.findAll();
    }

    public Car getCarByCarId(UUID carId) {
        return carRepository.findByCarId(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
    }

    public Car updateCarById(){
        ///  TODO: Implement Logic
        return null;
    }

    public Car deleteCarById(UUID carId){
        ///  TODO: Implement Logic
        return null;
    }

    @Transactional
    public void releaseCarFromBooking(UUID carId){

        /// Fetch Car to be put back in inventory
        Car carToRelease = getCarByCarId(carId);

        /// State change to the Car object - remove the car from booking.
        carToRelease.setCarBooked(false);

        ///  @Transactional manages the entity, to remove.
        this.carRepository.save(carToRelease);

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
