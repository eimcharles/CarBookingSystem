package com.eimc.car;

import com.eimc.car.dao.ListCarDAO;
import com.eimc.exception.CarNotFoundException;
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

public class ListCarDAOTest {

    private ListCarDAO actualTestListCarDAO;

    private Car expectedTestCarBMWElectric;
    private Car expectedTestCarBMWGas;
    private Car expectedTestCarVWElectric;
    private Car expectedTestCarHONDA;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestListCarDAO = new ListCarDAO();

        expectedTestCarBMWElectric =  new Car("123_1",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        expectedTestCarBMWGas = new Car("123_2",
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        expectedTestCarVWElectric = new Car("123_3",
                new BigDecimal("69.00"),
                Brand.VOLKSWAGEN,
                FuelType.ELECTRIC);

        expectedTestCarHONDA = new Car("123_4",
                new BigDecimal("49.00"),
                Brand.HONDA,
                FuelType.ELECTRIC);

    }

    @Test
    void getCarsCanReturnCarsAndHasCorrectSizeAndContent(){

        // GIVEN actualTestListCarDAO object created in setUp();

        // WHEN
        List<Car> actualTestCars = actualTestListCarDAO.getCars();

        // THEN
        assertThat(actualTestCars)
                .as("The getCars() method must return an list of 4 cars with the correct contents.")
                .isNotNull()
                .hasSize(4)
                .containsExactly(expectedTestCarBMWElectric, expectedTestCarBMWGas, expectedTestCarVWElectric, expectedTestCarHONDA);

    }

    @Test
    void getCarsCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestListCarDAO object created in setUp();

        // WHEN
        List<Car> actualTestCars = actualTestListCarDAO.getCars();
        actualTestCars.set(0, null);
        List<Car> actualTestCarsAfterModification = actualTestListCarDAO.getCars();

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
        assertThatThrownBy(() -> actualTestListCarDAO.updateCar(nonExistantCar))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessageContaining(nonExistantCar.getRegistrationNumber());

    }

}
