package com.eimc.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *      Unit tests for the Car domain class.
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class CarTest {

    private Car actualTestCar;
    private String actualTestRegistrationNumber;
    private BigDecimal actualTestRentalPricePerDay;
    private Brand actualTestBrand;
    private FuelType actualTestFuelType;


    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestRegistrationNumber = "123_1";
        actualTestRentalPricePerDay = new BigDecimal("89.00");
        actualTestBrand = Brand.BMW;
        actualTestFuelType = FuelType.ELECTRIC;

        actualTestCar = new Car(actualTestRegistrationNumber,
                actualTestRentalPricePerDay,
                actualTestBrand,
                actualTestFuelType);

    }

    @Test
    void constructorCanInitializeCarObject() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).isNotNull();

    }

    @Test
    void constructorCanInitializeCarRegistrationNumber() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("The registrationNumber attribute must be initialized correctly by the constructor.")
                .extracting(Car::getRegistrationNumber)             // Extracts the value of the 'getRegistrationNumber' attribute
                .isEqualTo(actualTestRegistrationNumber);

    }

    @Test
    void constructorCanInitializeCarRentalPricePerDay() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("The rentalPricePerDay attribute must be initialized correctly by the constructor.")
                .extracting(Car::getRentalPricePerDay)              // Extracts the value of the 'getRentalPricePerDay' attribute
                .isEqualTo(actualTestRentalPricePerDay);

    }

    @Test
    void constructorCanInitializeCarBrand(){

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("The brand attribute must be initialized correctly by the constructor.")
                .extracting(Car::getBrand)                         // Extracts the value of the 'getBrand' attribute
                .isEqualTo(actualTestBrand);

    }

    @Test
    void constructorCanInitializeCarFuelType() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("The fuelType attribute must be initialized correctly by the constructor.")
                .extracting(Car::getFuelType)                     // Extracts the value of the 'getFuelType' attribute
                .isEqualTo(actualTestFuelType);

    }

    @Test
    void constructorCanInitializeIsCarBookedStatus() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("The isCarBooked attribute must be initialized correctly by the constructor.")
                .extracting(Car::isCarBooked)                          // Extracts the value of the 'isBooked' attribute
                .isEqualTo(false);

    }

    @Test
    void equalsCanCheckEqualityWhenAttributesAreIdentical() {

        // GIVEN expectedTestCarCopy
        Car expectedTestCarCopy = new Car(actualTestRegistrationNumber,
                actualTestRentalPricePerDay,
                actualTestBrand,
                actualTestFuelType);

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Cars with identical attributes are equal")
                .isEqualTo(expectedTestCarCopy);

    }

    @Test
    void equalsCanCheckEqualityWhenRegistrationNumbersAreIdentical() {

        // GIVEN expectedTestCarWithSameRegistrationNumber
        Car expectedTestCarWithSameRegistrationNumber = new Car(actualTestRegistrationNumber,
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Cars with identical registrationNumber attributes are equal, even if other attributes are not identical")
                .isEqualTo(expectedTestCarWithSameRegistrationNumber);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingACarToItself() {

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Identical cars are equal to each other")
                .isEqualTo(actualTestCar);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingACarToNull() {

        // GIVEN expectedTestNullCar
        Car expectedTestNullCar = null;

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("A car is not equal to a Null Car object")
                .isNotEqualTo(expectedTestNullCar);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingDifferentCarsWithADifferentRegistrationNumber() {

        // GIVEN expectedDifferentCar
        Car expectedDifferentCar = new Car("123_2",
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Cars should not be equal if their registration number is different and their attributes are different")
                .isNotEqualTo(expectedDifferentCar);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingTheSameCarWithDifferentRegistrationNumber() {

        // GIVEN expectedDifferentCar
        Car expectedDifferentCar = new Car("123_2",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Cars should not be equal if their registration number is different, and their attributes are identical")
                .isNotEqualTo(expectedDifferentCar);

    }

    @Test
    void hashCodeCanCheckEqualityWhenAttributesAreIdentical() {

        // GIVEN expectedTestCarCopy
        Car expectedTestCarCopy = new Car(actualTestRegistrationNumber,
                actualTestRentalPricePerDay,
                actualTestBrand,
                actualTestFuelType);

        // WHEN actualTestCar object created in setUp();
        int actualTestCarHashCode = actualTestCar.hashCode();
        int expectedTestCarCopyHashCode = expectedTestCarCopy.hashCode();

        // THEN
        assertThat(actualTestCarHashCode).as("If cars are equal, their hash codes must be equal")
                .isEqualTo(expectedTestCarCopyHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenRegistrationNumbersAreIdentical() {

        // GIVEN expectedTestCarWithSameRegistrationNumber
        Car expectedTestCarWithSameRegistrationNumber = new Car(actualTestRegistrationNumber,
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN actualTestCar object created in setUp();
        int actualTestCarHashCode = actualTestCar.hashCode();
        int expectedTestCarCopyHashCode = expectedTestCarWithSameRegistrationNumber.hashCode();

        // THEN
        assertThat(actualTestCarHashCode).as("if cars are equal by registration number, even if their attributes are different, then their hash codes must be equal")
                .isEqualTo(expectedTestCarCopyHashCode);

    }

    @Test
    void hashCodeCanCheckInequalityWhenRegistrationNumbersAreDifferent() {

        // GIVEN expectedDifferentCar
        Car expectedDifferentCar = new Car("123_2",
                actualTestRentalPricePerDay,
                actualTestBrand,
                actualTestFuelType);

        // WHEN
        int actualTestCarHashCode = actualTestCar.hashCode();
        int expectedTestCarCopyHashCode = expectedDifferentCar.hashCode();

        // THEN
        assertThat(actualTestCarHashCode).as("If cars are not equal by registration number, and their attributes are identical, their hash codes should be different")
                .isNotEqualTo(expectedTestCarCopyHashCode);

    }

    @Test
    void setBookedCanUpdateIsBookedStatusToBooked() {

        // GIVEN: actualTestCar is currently unbooked (false)

        // WHEN
        actualTestCar.setCarBooked(true);

        // THEN
        assertThat(actualTestCar.isCarBooked()).as("The setCarBooked() method should change the isCarBooked state to true.")
                .isTrue();

    }

    @Test
    void setBookedCanUpdateIsBookedStatusToNotBooked() {

        // GIVEN: actualTestCar is currently booked (true)
        actualTestCar.setCarBooked(true);

        // WHEN
        actualTestCar.setCarBooked(false);

        // THEN
        assertThat(actualTestCar.isCarBooked()).as("The setCarBooked(false) method should change the isCarBooked state back to false.")
                .isFalse();

    }
}
