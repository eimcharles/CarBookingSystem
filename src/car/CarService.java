package car;

import car.dao.ArrayCarDAO;
import exception.CarNotFoundException;
import exception.CarUnavailableException;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

public class CarService {

    private final ArrayCarDAO arrayCarDAO;

    public CarService(ArrayCarDAO arrayCarDAO) {
        this.arrayCarDAO = arrayCarDAO;
    }

    /**
     *      Cancels the association of a single Car by its registration number to a single booking.
     *
     *      @param registrationNumber The unique registration number of the car to cancel and release.
     *
     *      @throws CarNotFoundException if the car does not exist in the inventory.
     *
     *      @throws CarUnavailableException if the car exists but is not currently marked as booked .
     */

    public void cancelAssociatedCarByRegistrationNumber(String registrationNumber){

        // Fetch Car to be put back in inventory
        Car carToRelease = getCarByRegistrationNumber(registrationNumber);

        // Make sure the car is booked
        if (!carToRelease.isBooked()) {
            throw new CarUnavailableException(registrationNumber);
        }

        // State change to the Car object - remove the car from booking.
        carToRelease.setBooked(false);

        // Update the state change - holds car cancellation confirmation status
        boolean isCancelledAndUpdated = this.arrayCarDAO.updateCar(carToRelease);

        // Car couldn't be cancelled and put back in inventory
        if (!isCancelledAndUpdated) {
            throw new CarNotFoundException(registrationNumber);
        }
    }

    /**
     *      Retrieves a single Car object using its registration number.
     *
     *      @param registrationNumber The unique identifier of the car to find.
     *
     *      @return The Car object matching the given registration number.
     *
     *      @throws CarNotFoundException if no car exists with the specified registration number.
     */

    public Car getCarByRegistrationNumber(String registrationNumber) {
        for (Car car: getCars()){
            if (car.getRegistrationNumber().equals(registrationNumber)){
                return car;
            }
        }

        throw new CarNotFoundException(registrationNumber);

    }


    /**
     *      Retrieves all Car objects from the arrayCarDAO class, filtering out any null references that
     *      may exist, and returns a compacted array of Cars.
     *
     *      @return A new, compacted array of Car objects,
     *      or an empty array if no users are found or all are null.
     */

    public Car[] getCars() {

        // Get all cars from DAO
        Car[] cars = this.arrayCarDAO.getCars();

        // If cars is null or empty, return empty array
        if (cars == null || cars.length == 0){
            return new Car[0];
        }

        // Number of non-null cars
        int nonNullCarCount = getCarCount(cars);

        if (nonNullCarCount == 0){
            return new Car[0];
        }

        // Create a new array with the nonNullCarCount for size
        Car[] nonNullCars = new Car[nonNullCarCount];

        // Populates a pre-sized Car array with nonNullCars
        populateCars(cars, nonNullCars);

        return nonNullCars;
    }

    private int getCarCount(Car[] cars) {

        // Count non-null Cars in DAO array
        int nonNullCarCount = 0;
        for (Car car : cars) {
            if (car != null){
                nonNullCarCount++;
            }
        }

        return nonNullCarCount;
    }

    private void populateCars(Car[] cars, Car[] nonNullCars) {

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through cars
        for (int i = 0; i < cars.length; i++) {

            // Use the car at the current index of the source array
            Car nonNullCar = cars[i];

            // Add to nonNullCars
            if (nonNullCar != null){
                nonNullCars[index++] = nonNullCar;
            }
        }
    }

    /**
     *      Retrieves an array of all Car objects that are currently available (not booked).
     *
     *      @return A new, compacted array containing only
     *      available Car objects, or an empty array if none are available.
     */

    public Car[] getAllAvailableCars() {

        // Get all cars that are non-null
        Car[] nonNullCars = getCars();

        // If cars is null or empty, return empty array
        if (nonNullCars == null || nonNullCars.length == 0){
            return new Car[0];
        }

        // Number of available cars
        int availableCarCount = getAllAvailableCarCount(nonNullCars);

        if (availableCarCount == 0){
            return new Car[0];
        }

        // Create a new array with the availableCarCount for size
        Car[] availableCars = new Car[availableCarCount];

        // Populates a pre-sized Car array with availableCars
        populateAllAvailableCars(nonNullCars, availableCars);

        return availableCars;
    }

    private int getAllAvailableCarCount(Car[] cars) {

        // Count not booked cars
        int availableCarCount = 0;
        for (Car car : cars) {
            if (car != null && !car.isBooked()){
                availableCarCount++;
            }
        }
        return availableCarCount;    }

    private void populateAllAvailableCars(Car[] nonNullCars, Car[] availableCars) {

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through nonNullCars
        for (int i = 0; i < nonNullCars.length; i++) {

            // Use the nonNullCar at the current index of the source array
            Car availableCar = nonNullCars[i];

            // Add to availableCars if the is not booked
            if (availableCar != null && !availableCar.isBooked()){
                availableCars[index++] = availableCar;
            }
        }
    }

    /**
     *      Retrieves an array of all Car objects that are currently available (not booked)
     *      by their fuel type.
     *
     *      @return A new, compacted array containing only available Car objects
     *      by their fuel type, or an empty array if none are available.
     */


    public Car[] getAllAvailableCarsByFuelType(FuelType fuelType){

        // Get all availableCars that are not booked
        Car[] availableCars = getAllAvailableCars();

        // If availableCars is null or empty, return empty array
        if (availableCars == null || availableCars.length == 0){
            return new Car[0];
        }

        // Number of availableCars by passed fuelType
        int carCountByFuelType = getCarCountByFuelType(availableCars, fuelType);

        if (carCountByFuelType == 0){
            return new Car[0];
        }

        // Create a new array with the carCountByFuelType for size
        Car[] filteredCarsByFuelType = new Car[carCountByFuelType];

        // Populates a pre-sized Car array with availableCars that match the specified FuelType.
        populateCarsByFuelType(availableCars, filteredCarsByFuelType , fuelType);

        return filteredCarsByFuelType;

    }

    private int getCarCountByFuelType(Car[] availableCars, FuelType fuelType){

        // Count the availableCars based on fuel type in availableCars array
        int carCountByFuelType = 0;
        for (Car car : availableCars) {
            if (car.getFuelType() == fuelType){
                carCountByFuelType++;
            }
        }

        return carCountByFuelType;

    }

    private void populateCarsByFuelType(Car[] availableCars, Car[] filteredCarsByFuelType, FuelType fuelType){

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through all the availableCars
        for (int i = 0; i < availableCars.length; i++) {

            // Use the car at the current index of the source array
            Car currentCar = availableCars[i];

            // if the car at an index matches the passed fuelType, add it to filteredCarsByFuelType array
            if (currentCar.getFuelType() == fuelType){
                filteredCarsByFuelType[index++] = currentCar;

            }
        }
    }

    public Car[] getAllElectricCars() {
        return getAllAvailableCarsByFuelType(FuelType.ELECTRIC);
    }

    public Car[] getAllGasolineCars() {
        return getAllAvailableCarsByFuelType(FuelType.GASOLINE);
    }
}
