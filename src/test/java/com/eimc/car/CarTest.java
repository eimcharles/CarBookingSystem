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

    // The result we are testing (ACTUAL)
    private Car actualTestCar;

    // The data we use to compare (EXPECTED)
    private String expectedRegistrationNumber;
    private BigDecimal expectedRentalPricePerDay;
    private Brand expectedBrand;
    private FuelType expectedFuelType;

    @BeforeEach
    void setUp(){

        // GIVEN: Set up the expected data
        expectedRegistrationNumber = "123_1";
        expectedRentalPricePerDay = new BigDecimal("89.00");
        expectedBrand = Brand.BMW;
        expectedFuelType = FuelType.ELECTRIC;

        // ACT: Construct the actual car
        actualTestCar = new Car(expectedRegistrationNumber,
                expectedRentalPricePerDay,
                expectedBrand,
                expectedFuelType);

    }

    @Test
    void constructorShouldCorrectlyInitializeAllFields() {
        // GIVEN & WHEN: Handled in setUp()

        // THEN: Use AssertJ Fluent API for a "One-Stop-Shop" check
        assertThat(actualTestCar)
                .as("The Car object must be fully and correctly initialized")
                .isNotNull()
                .returns(expectedRegistrationNumber, Car::getRegistrationNumber)
                .returns(expectedRentalPricePerDay, Car::getRentalPricePerDay)
                .returns(expectedBrand, Car::getBrand)
                .returns(expectedFuelType, Car::getFuelType)
                .returns(false, Car::isCarBooked);
    }

    @Test
    void equalsCanCheckEqualityWhenCarsAreIdentical() {

        // GIVEN
        Car identicalCar = new Car(expectedRegistrationNumber,
                expectedRentalPricePerDay,
                expectedBrand,
                expectedFuelType);

        // WHEN actualTestCar object created in setUp();

        // THEN
        assertThat(actualTestCar).as("Cars with identical attributes are equal")
                .isEqualTo(identicalCar);

    }

    @Test
    void equalsCanCheckEqualityWhenRegistrationNumbersAreIdentical() {

        // GIVEN
        Car carWithSameRegistrationNumber = new Car(expectedRegistrationNumber,
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN & THEN
        assertThat(actualTestCar).as("Cars with identical registrationNumber attributes are equal, even if other attributes are not identical")
                .isEqualTo(carWithSameRegistrationNumber);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingACarToItself() {

        // WHEN & THEN
        assertThat(actualTestCar).as("Identical cars are equal to each other")
                .isEqualTo(actualTestCar);

    }

    @Test
    void equalsCanCheckInequalityWhenACarIsNull() {

        // GIVEN nullCar
        Car nullCar = null;

        // WHEN & THEN
        assertThat(actualTestCar).as("A car is not equal to a Null Car object")
                .isNotEqualTo(nullCar);

    }

    @Test
    void equalsCanCheckInequalityWhenRegistrationNumbersAreDifferent() {

        // GIVEN
        Car carWithDifferentRegistrationNumber = new Car("123_2",
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN & THEN
        assertThat(actualTestCar).as("Cars should not be equal if their registration number is different and their attributes are different")
                .isNotEqualTo(carWithDifferentRegistrationNumber);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingDifferentCars() {

        // GIVEN
        Car carWithDifferentRegistrationNumber = new Car("123_2",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        // WHEN & THEN
        assertThat(actualTestCar).as("Cars should not be equal if their registration number is different, and their attributes are identical")
                .isNotEqualTo(carWithDifferentRegistrationNumber);

    }

    @Test
    void hashCodeCanCheckEqualityWhenCarsAreIdentical() {

        // GIVEN
        Car identicalCar = new Car(expectedRegistrationNumber,
                expectedRentalPricePerDay,
                expectedBrand,
                expectedFuelType);

        // WHEN
        int actualCarHashCode = actualTestCar.hashCode();
        int identicalCarHashCode = identicalCar.hashCode();

        // THEN
        assertThat(actualCarHashCode).as("If cars are equal, their hash codes must be equal")
                .isEqualTo(identicalCarHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenRegistrationNumbersAreIdentical() {

        // GIVEN
        Car carWithSameRegistrationNumber = new Car(expectedRegistrationNumber,
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        // WHEN
        int actualCarHashCode = actualTestCar.hashCode();
        int identicalCarHashCode = carWithSameRegistrationNumber.hashCode();

        // THEN
        assertThat(actualCarHashCode).as("if cars are equal by registration number, even if their attributes are different, then their hash codes must be equal")
                .isEqualTo(identicalCarHashCode);

    }

    @Test
    void hashCodeCanCheckInequalityWhenRegistrationNumbersAreDifferent() {

        // GIVEN
        Car differentCar = new Car("123_2",
                expectedRentalPricePerDay,
                expectedBrand,
                expectedFuelType);

        // WHEN
        int actualCarHashCode = actualTestCar.hashCode();
        int differentCarHashCode = differentCar.hashCode();

        // THEN
        assertThat(actualCarHashCode).as("If cars are not equal by registration number, and their attributes are identical, their hash codes should be different")
                .isNotEqualTo(differentCarHashCode);

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

        // GIVEN: A car is initialized as not booked
        actualTestCar.setCarBooked(true);

        // WHEN
        actualTestCar.setCarBooked(false);

        // THEN
        assertThat(actualTestCar.isCarBooked()).as("The setCarBooked(false) method should change the isCarBooked state back to false.")
                .isFalse();

    }
}
