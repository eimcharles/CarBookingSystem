package com.eimc.car;

/**
 *      Domain class for Car Object
 * */

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private String registrationNumber;
    private BigDecimal rentalPricePerDay;
    private Brand manufacturer;
    private FuelType fuelType;
    private boolean isBooked;


    public Car(String registrationNumber, BigDecimal rentalPricePerDay, Brand manufacturer, FuelType fuelType) {
        this.registrationNumber = registrationNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.manufacturer = manufacturer;
        this.fuelType = fuelType;
        this.isBooked = false;
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

    public Brand getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Brand manufacturer) {
        this.manufacturer = manufacturer;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public boolean isElectric() { return this.fuelType == FuelType.ELECTRIC; }

    public boolean isGasoline() { return this.fuelType == FuelType.GASOLINE; }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isBooked == car.isBooked && Objects.equals(registrationNumber, car.registrationNumber) &&
                Objects.equals(rentalPricePerDay, car.rentalPricePerDay) &&
                manufacturer == car.manufacturer
                && fuelType == car.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, rentalPricePerDay, manufacturer, fuelType, isBooked);
    }

    @Override
    public String toString() {
        return "Car { registrationNumber = '%s' , rentalPricePerDay = %s , manufacturer = %s, fuelType = %s, booked = %b}".formatted(registrationNumber, rentalPricePerDay, manufacturer, fuelType, isBooked);
    }
}
