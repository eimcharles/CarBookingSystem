package com.eimc.car.dto;

import com.eimc.car.model.Car;
import com.eimc.car.model.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CarDTO(

        UUID carId,
        Model model,
        Brand brand,
        Integer year,
        String color,
        Integer horsepower,
        Transmission transmission,
        FuelType fuelType,
        CarStatus status,
        BigDecimal rentalPricePerDay,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime createdAt

) {

    public Car toEntity() {

        Car car = new Car(
                this.carId,
                this.model,
                this.rentalPricePerDay
        );

        car.setYear(this.year);
        car.setColor(this.color);
        car.setHorsepower(this.horsepower);
        car.setTransmission(this.transmission);
        car.setFuelType(this.fuelType);

        return car;
    }

    public static CarDTO fromEntity(Car car) {

        return new CarDTO(

                car.getCarId(),
                car.getModel(),
                car.getBrand(),
                car.getYear(),
                car.getColor(),
                car.getHorsepower(),
                car.getTransmission(),
                car.getFuelType(),
                car.getStatus(),
                car.getRentalPricePerDay(),
                car.getCreatedAt()

        );

    }
}
