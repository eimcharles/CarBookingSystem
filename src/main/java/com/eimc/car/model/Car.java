package com.eimc.car.model;

/**
 *      Domain class for Car Object
 * */

import com.eimc.car.model.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID carId;

    /// TODO: add an imageUrl for the car profile

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    /// TODO: look at potentially changing the model from enum to string
    private Model model;

    private Integer year;
    private String color;
    private Integer horsepower;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private BigDecimal rentalPricePerDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public Car() {}

    public Car(UUID carId, Model model, BigDecimal rentalPricePerDay) {
        this.carId = carId;
        this.setModel(model);
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = CarStatus.AVAILABLE;
    }

    @PrePersist
    public void prePersist() {
        if (this.carId == null) this.carId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    }

    public void setModel(Model model) {
        this.model = model;
        if (model != null) {
            this.brand = model.getBrand();
        }
    }

    public boolean isCarBooked() {
        return this.status.isBooked();
    }

    public void setCarBooked(boolean carBooked) {
        if (carBooked) {
            this.status = CarStatus.BOOKED;
        } else {
            this.status = CarStatus.AVAILABLE;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public UUID getCarId() {
        return carId;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public CarStatus getStatus() {
        return status;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }

    @Override
    public String toString() {
        return "Car {id = %d, carId = %s, model = %s %s}"
                .formatted(id, carId, brand, model);
    }
}
