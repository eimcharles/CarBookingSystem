package com.eimc.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *      Unit tests for the ListCarDAO class
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class CarRepositoryTest {

    private CarRepository actualTestCarRepository;

    private Car expectedTestCarHONDA;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestCarRepository = new CarRepository();


        expectedTestCarHONDA = new Car("123_4",
                new BigDecimal("49.00"),
                Brand.HONDA,
                FuelType.ELECTRIC);

    }

    @Test
    void getCarsCanReturnCarsAndHasCorrectSizeAndContent(){

        // GIVEN actualTestListCarDAO object created in setUp();

        // WHEN
        List<Car> actualTestCars = actualTestCarRepository.getCars();

        // THEN
        assertThat(actualTestCars)
                .as("The getCars() method must return an list of 4 cars with the correct contents.")
                .isNotNull()
                .hasSize(1)
                .containsExactly(expectedTestCarHONDA);

    }

    @Test
    void getCarsCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestListCarDAO object created in setUp();

        // WHEN
        List<Car> actualTestCars = actualTestCarRepository.getCars();
        actualTestCars.set(0, null);
        List<Car> actualTestCarsAfterModification = actualTestCarRepository.getCars();

        // THEN
        assertThat(actualTestCarsAfterModification.get(0))
                .as("The element at index 0 in the internal state of actualTestListCarDAO state should not be null")
                .isNotNull();

    }

    @ParameterizedTest
    @CsvSource(

            {

                    "123_6",
                    "123_$",
                    "_",
                    "&",
                    "b10d126a-3608-4980-9f9c-aa179f5cebc3",

            }

    )
    void updateCarCanThrowCarNotFoundExceptionWhenRegistrationDoesntExist(String expectedNotFoundRegistrationNumber){

        // GIVEN
        Car nonExistantCar = new Car(
                expectedNotFoundRegistrationNumber,
                new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC);

        /**
         *     Functional Programming:
         *
         *     Asserts that calling updateCar() with a non-existent car (within the lambda)
         *     throws a CarNotFoundException, and verifies the exception message contains the
         *     non-existent registration number.
         * */

        // WHEN & THEN
        assertThatThrownBy(() -> actualTestCarRepository.updateCar(nonExistantCar))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessageContaining(nonExistantCar.getRegistrationNumber());

    }

}
