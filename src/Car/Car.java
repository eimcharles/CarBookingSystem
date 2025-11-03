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

    private boolean isElectric;

    public Car(String registrationNumber, BigDecimal rentalPricePerDay, Brand manufacturer, boolean isElectric) {
        this.registrationNumber = registrationNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.manufacturer = manufacturer;
        this.isElectric = isElectric;
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

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isElectric == car.isElectric &&
                Objects.equals(registrationNumber, car.registrationNumber) &&
                Objects.equals(rentalPricePerDay, car.rentalPricePerDay) && manufacturer == car.manufacturer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, rentalPricePerDay, manufacturer, isElectric);
    }

    @Override
    public String toString() {
        return "Car { registrationNumber = '%s' , rentalPricePerDay = %s , manufacturer = %s, isElectric = %s }".formatted(registrationNumber, rentalPricePerDay, manufacturer, isElectric);
    }
}
