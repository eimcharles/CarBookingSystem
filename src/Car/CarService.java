package Car;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car[] getCarsDAO() {
        return CarDAO.getCarDAO();
    }

    public Car getCarsByIdFromDAO(String registrationNumber) {
        for (Car car: getCarsDAO()){
            if (car.getRegistrationNumber().equals(registrationNumber)){
                return car;
            }
        }
        throw new IllegalStateException(String.format("Car with registration number %s not found", registrationNumber));
    }

    public Car[] getElectricCars(){

        int electricCarCount = 0;

        // Get all cars
        Car[] cars = getCarsDAO();

        // If cars is null or empty, return empty array
        if (cars == null || cars.length == 0){
            return new Car[0];
        }

        // Count the electric cars in cars array
        for (Car car : cars) {
            if (car.isElectric()){
                electricCarCount++;
            }
        }

        // If no electric cars are found, return empty array
        if (electricCarCount == 0){
            return new Car[0];
        }

        // Create a new array with the electricCarCount for size
        Car[] electricCars = new Car[electricCarCount];

        // Avoids null gaps, skipped slots
        int index = 0;

        // Look through all the cars
        for (int i = 0; i < cars.length; i++) {

            // if the car at an index isElectric, add it to electricCars and move to the next index
            if (cars[i].isElectric()){
                electricCars[index++] = cars[i];

            }
        }

        return electricCars;
    }

}
