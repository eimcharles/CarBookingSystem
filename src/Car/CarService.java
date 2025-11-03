package Car;

import User.User;

import java.util.UUID;

/**
 *      Service class for managing Car objects.
 *      Contains business logic related to cars.
 */

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car[] getCarDAO() {
        return CarDAO.getCarDAO();
    }

    public Car getCarsByIdFromDAO(String registrationNumber) {
        for (Car car: getCarDAO()){
            if (car.getRegistrationNumber().equals(registrationNumber)){
                return car;
            }
        }
        throw new IllegalStateException(String.format("Car with registration number %s not found", registrationNumber));
    }

}
