package com.eimc.car;

/**
 *      Domain class for Car Object
 *
 *      TODO fix Car entity model for Spring Boot

 * */

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private String registrationNumber;
    private BigDecimal rentalPricePerDay;
    private Brand brand;
    private FuelType fuelType;
    private boolean isCarBooked;

    public Car(String registrationNumber, BigDecimal rentalPricePerDay, Brand brand, FuelType fuelType) {
        this.registrationNumber = registrationNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.fuelType = fuelType;
        this.isCarBooked = false;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public boolean isElectric() { return this.fuelType == FuelType.ELECTRIC; }

    public boolean isGasoline() { return this.fuelType == FuelType.GASOLINE; }

    public boolean isCarBooked() {
        return isCarBooked;
    }

    public void setCarBooked(boolean carBooked) {
        this.isCarBooked = carBooked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(registrationNumber, car.registrationNumber);

    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return "Car { registrationNumber = '%s' , rentalPricePerDay = %s , manufacturer = %s, fuelType = %s, isCarBooked = %b}".formatted(registrationNumber, rentalPricePerDay, brand, fuelType, isCarBooked);
    }
}
