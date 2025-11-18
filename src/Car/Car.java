package Car;

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

    public Car(String registrationNumber, BigDecimal rentalPricePerDay, Brand manufacturer, FuelType fuelType) {
        this.registrationNumber = registrationNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.manufacturer = manufacturer;
        this.fuelType = fuelType;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return fuelType == car.fuelType &&
                Objects.equals(registrationNumber, car.registrationNumber) &&
                Objects.equals(rentalPricePerDay, car.rentalPricePerDay) && manufacturer == car.manufacturer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, rentalPricePerDay, manufacturer, fuelType);
    }

    @Override
    public String toString() {
        return "Car { registrationNumber = '%s' , rentalPricePerDay = %s , manufacturer = %s, fuelType = %s }".formatted(registrationNumber, rentalPricePerDay, manufacturer, fuelType);
    }
}
