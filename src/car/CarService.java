package car;

import car.dao.ArrayCarDAO;
import exception.CarNotFoundException;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 *
 *      TODO : add comments for methods in CarService
 */

public class CarService {

    private final ArrayCarDAO arrayCarDAO;

    public CarService(ArrayCarDAO arrayCarDAO) {
        this.arrayCarDAO = arrayCarDAO;
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        for (Car car: getCars()){
            if (car.getRegistrationNumber().equals(registrationNumber)){
                return car;
            }
        }

        throw new CarNotFoundException(registrationNumber);

    }

    public Car[] getCars() {

        // Get all cars from DAO
        Car[] cars = this.arrayCarDAO.getCars();

        // If cars is null or empty, return empty array
        if (cars == null || cars.length == 0){
            return new Car[0];
        }


        int nonNullCarCount = getNonNullCarCount(cars);

        if (nonNullCarCount == 0){
            return new Car[0];
        }

        Car[] nonNullCars = populateNonNullCars(nonNullCarCount, cars);

        return nonNullCars;
    }

    private Car[] populateNonNullCars(int nonNullCarCount, Car[] cars) {

        // Create a new array with the nonNullCarCount for size
        Car[] nonNullCars = new Car[nonNullCarCount];

        int index = 0;
        for (int i = 0; i < cars.length; i++) {

            Car nonNullCar = cars[i];

            if (nonNullCar != null){
                nonNullCars[index++] = nonNullCar;
            }
        }
        return nonNullCars;

    }

    private int getNonNullCarCount(Car[] cars) {

        // Count non-null Cars in DAO array
        int nonNullCarCount = 0;
        for (Car car : cars) {
            if (car != null){
                nonNullCarCount++;
            }
        }
        return nonNullCarCount;    }

    public Car[] getElectricCars() {
        return getCarsByFuelType(FuelType.ELECTRIC);
    }

    public Car[] getGasolineCars() {
        return getCarsByFuelType(FuelType.GASOLINE);
    }

    public Car[] getCarsByFuelType(FuelType fuelType){

        // Get all cars
        Car[] cars = getCars();

        // If cars is null or empty, return empty array
        if (cars == null || cars.length == 0){
            return new Car[0];
        }

        // Number of cars by passed fuelType
        int carCountByFuelType = countCarsByFuelType(cars, fuelType);

        // Create a new array with the carCountByFuelType for size
        Car[] filteredCarsByFuelType = new Car[carCountByFuelType];

        // Populates a pre-sized Car array with cars that match the specified FuelType.
        populateCarsByFuelType(cars, fuelType, filteredCarsByFuelType);

        return filteredCarsByFuelType;

    }

    private int countCarsByFuelType(Car[] cars, FuelType fuelType){

        int carCountByFuelType = 0;

        // Count the cars based on fuel type in cars array
        for (Car car : cars) {
            if (car.getFuelType() == fuelType){
                carCountByFuelType++;
            }
        }

        return carCountByFuelType;

    }

    private void populateCarsByFuelType(Car[] cars, FuelType fuelType, Car[] filteredCarsByFuelType){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the cars
        for (int i = 0; i < cars.length; i++) {

            // Use the car at the current index of the source array
            Car currentCar = cars[i];

            // if the car at an index matches the passed fuelType, add it to filteredCarsByFuelType
            if (currentCar.getFuelType() == fuelType){
                filteredCarsByFuelType[index++] = currentCar;

            }
        }
    }

}
