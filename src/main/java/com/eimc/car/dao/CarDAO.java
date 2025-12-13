package com.eimc.car.dao;

import com.eimc.car.Car;

/**
 *      CarDAO defines the contract for all data
 *      access operations related to Car entities
 * */

public interface CarDAO {

    Car[] getCars();

    void updateCar(Car carToUpdate);

}
