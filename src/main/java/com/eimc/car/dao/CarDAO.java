package com.eimc.car.dao;

import com.eimc.car.Car;

import java.util.List;

/**
 *      CarDAO defines the contract for all data
 *      access operations related to Car entities
 * */

public interface CarDAO {

    void addCar(Car car);

    void updateCar(Car carToUpdate);

    List<Car> getCars();

}
