package Car;

import java.util.NoSuchElementException;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        for (Car car: getAllCars()){
            if (car.getRegistrationNumber().equals(registrationNumber)){
                return car;
            }
        }

        throw new NoSuchElementException(String.format("Car with registration number %s not found", registrationNumber));

    }

    public Car[] getCarsByFuelType(FuelType fuelType){

        // Get all cars
        Car[] cars = getAllCars();

        // If cars is null or empty, return empty array
        if (cars == null || cars.length == 0){
            return new Car[0];
        }

        // Number of cars by passed fuelType
        int carCount = countCarsByFuelType(cars, fuelType);

        // Create a new array with the carCount for size
        Car[] filteredCars = new Car[carCount];

        // Populates a pre-sized Car array with cars that match the specified FuelType.
        populateCarsByFuelType(cars, fuelType, filteredCars);

        return filteredCars;

    }

    public Car[] getAllCars() {
        // Passing the copy of Car objects returned from DAO layer
        return this.carDAO.getCarsFromDAO();
    }

    public Car[] getElectricCars() {
        return getCarsByFuelType(FuelType.ELECTRIC);
    }

    public Car[] getGasolineCars() {
        return getCarsByFuelType(FuelType.GASOLINE);
    }

    private int countCarsByFuelType(Car[] cars, FuelType fuelType){

        int carCount = 0;

        // Count the cars based on fuel type in cars array
        for (Car car : cars) {
            if (car.getFuelType() == fuelType){
                carCount++;
            }
        }

        return carCount;

    }

    private void populateCarsByFuelType(Car[] cars, FuelType fuelType, Car[] filteredCars){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the cars
        for (int i = 0; i < cars.length; i++) {

            // Use the car at the current index of the source array
            Car currentCar = cars[i];

            // if the car at an index matches the passed fuelType, add it to filteredCars
            if (currentCar.getFuelType() == fuelType){
                filteredCars[index++] = currentCar;

            }
        }
    }

}
