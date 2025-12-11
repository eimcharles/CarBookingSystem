package com.eimc.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 *     Unit tests for the Car domain class.
 */

public class CarTest {

    private Car testCar;
    private String registrationNumber;
    private BigDecimal price;

    @BeforeEach
    void setUp() {

        // GIVEN: Initialize all important parameters
        registrationNumber = "123_1";
        price = new BigDecimal("59.99");

        // GIVEN: The primary object is instantiated
        testCar = new Car(registrationNumber, price, Brand.BMW, FuelType.GASOLINE);
    }

    @Test
    void constructor_ShouldInitializeAllFieldsCorrectly() {

        assertEquals(registrationNumber, testCar.getRegistrationNumber());
        assertEquals(price, testCar.getRentalPricePerDay());
        assertEquals(Brand.BMW, testCar.getManufacturer());
        assertEquals(FuelType.GASOLINE, testCar.getFuelType());
        assertFalse(testCar.isBooked(), "A Car is not be booked by default.");
    }

    @Test
    void setBooked_ShouldUpdateIsBookedState() {

        // GIVEN: A car is initially not booked (isBooked = false)
        assertFalse(testCar.isBooked());

        // WHEN: The setBooked() method is called with 'true'
        testCar.setBooked(true);

        // THEN: The isBooked() getter must return 'true'
        assertTrue(testCar.isBooked(), "setBooked(true) correctly updates the booked status of the Car.");
    }

    @Test
    void isElectric_ShouldReturnTrue_WhenFuelTypeIsElectric() {

        // GIVEN: A car is initialized with FuelType.ELECTRIC
        Car electricCar = new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC);

        // WHEN: We call the isElectric() method
        // THEN: The method must return true
        assertTrue(electricCar.isElectric(), "isElectric() must return true for ELECTRIC fuel type.");
        assertFalse(electricCar.isGasoline(), "An electric car must not be considered GASOLINE.");
    }

    @Test
    void isGasoline_ShouldReturnTrue_WhenFuelTypeIsGasoline() {

        // GIVEN: A car is initialized with FuelType.GASOLINE
        Car gasolineCar = new Car("123_2", new BigDecimal("79.00"), Brand.BMW, FuelType.GASOLINE);

        // WHEN: We call the isGasoline() method
        // THEN: The method must return true
        assertTrue(gasolineCar.isGasoline(), "isGasoline() must return true for GASOLINE fuel type.");
        assertFalse(gasolineCar.isElectric(), "A gasoline car must not be considered ELECTRIC.");
    }

    @Test
    void equals_ShouldReturnTrue_AndHashCodesMustMatch_WhenAllFieldsAreIdentical() {

        // GIVEN: Two separate Car objects with identical field values
        Car testCarCopy = new Car(registrationNumber, price, Brand.BMW, FuelType.GASOLINE);

        // WHEN: We compare the two objects using equals()
        // THEN: equals() must return true, AND their hash codes MUST be equal
        assertTrue(testCar.equals(testCarCopy),
                "Cars with identical fields must be considered equal.");
        assertEquals(testCar.hashCode(), testCarCopy.hashCode(),
                "If Cars are equal, their hash codes MUST be equal.");
    }

    @Test
    void equals_ShouldReturnTrue_WhenComparedToItself() {

        // GIVEN: A Car object (testCar)
        // WHEN: We compare the object to itself
        // THEN: equals() must return true (Reflexivity)
        assertTrue(testCar.equals(testCar),
                "An Car must be equal to itself.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenRegistrationNumberDiffers() {

        // GIVEN: A Car object and another with a different registrationNumber
        Car testCarCopyWithDifferentRegistrationNumber =
                new Car("123_5", new BigDecimal("59.00"), Brand.HONDA, FuelType.GASOLINE);

        // WHEN: We compare the two objects using equals()
        // THEN: equals() must return false
        assertFalse(testCar.equals(testCarCopyWithDifferentRegistrationNumber),
                "Cars should not be equal if registrationNumber differ.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenIsBookedDiffers() {

        // GIVEN: A Car object (testCar) and a Car object (testBookedCar) that is booked
        Car testBookedCar = new Car(registrationNumber, price, Brand.BMW, FuelType.GASOLINE);
        testBookedCar.setBooked(true);

        // WHEN: We compare the two objects using equals()
        // THEN: equals() must return false, as 'isBooked' is included in the equality check
        assertFalse(testCar.equals(testBookedCar),
                "Cars should not be equal if isBooked status differs.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenComparedAgainstNull() {

        // GIVEN: A Car object (testCar)
        // WHEN: We compare the object against null
        // THEN: equals() must return false
        assertFalse(testCar.equals(null),
                "Cars should not be equal to null.");
    }
}
