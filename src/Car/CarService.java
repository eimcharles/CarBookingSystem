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

        Car[] cars = getCarsDAO();

        // If cars is null or empty, return empty array
        if (cars == null && cars.length == 0){
            return new Car[0];
        }

        // Counts electric cars
        for (Car car : cars) {
            if (car.isElectric()){
                electricCarCount++;
            }
        }

        // If no electric cars found, return empty array
        if (electricCarCount == 0){
            return new Car[0];
        }

        // Holds the number of electric cars
        Car[] electricCars = new Car[electricCarCount];

        // Avoids null gaps, skipped slots
        int index = 0;

        // Copies all electric from carsDAO
        for (int i = 0; i < cars.length; i++) {

            // Only increments if isElectric
            if (cars[i].isElectric()){
                electricCars[index++] = cars[i];

            }
        }

        return electricCars;
    }

}
