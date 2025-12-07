package car.dao;

import booking.Booking;
import car.Car;

/**
 *      CarDAO defines the contract for all data
 *      access operations related to Car entities
 * */

public interface CarDAO {

    Car[] getCars();

    boolean updateCar(Car carToUpdate);

}
