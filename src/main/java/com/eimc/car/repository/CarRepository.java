package com.eimc.car.repository;

import com.eimc.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByCarId(UUID carId);

}
