package com.eimc.car;

import com.eimc.car.dao.ArrayCarDAO;
import com.eimc.exception.CarNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *      Unit tests for the ArrayCarDAO class
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class ArrayCarDAOTest {

    private ArrayCarDAO actualTestArrayCarDAO;

    private Car actualTestCarBMWElectric;
    private Car actualTestCarBMWGas;
    private Car actualTestCarVWElectric;
    private Car actualTestCarHONDA;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestArrayCarDAO = new ArrayCarDAO();

        actualTestCarBMWElectric =  new Car("123_1",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        actualTestCarBMWGas = new Car("123_2",
                new BigDecimal("79.00"),
                Brand.BMW,
                FuelType.GASOLINE);

        actualTestCarVWElectric = new Car("123_3",
                new BigDecimal("69.00"),
                Brand.VOLKSWAGEN,
                FuelType.ELECTRIC);

        actualTestCarHONDA = new Car("123_4",
                new BigDecimal("49.00"),
                Brand.HONDA,
                FuelType.ELECTRIC);

    }

    @Test
    void getCarCanReturnCarsAndHasCorrectSizeAndContent(){

        // WHEN
        Car[] actualTestCars = actualTestArrayCarDAO.getCars();

        // THEN
        assertThat(actualTestCars)
                .as("The getCars() method must return an array of 4 cars with the correct contents.")
                .isNotNull()
                .hasSize(4)
                .containsExactly(actualTestCarBMWElectric, actualTestCarBMWGas, actualTestCarVWElectric, actualTestCarHONDA);

    }

    @Test
    void getCarsCanReturnADefensiveCopyExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestCars
        Car[] actualTestCars = actualTestArrayCarDAO.getCars();

        // WHEN
        actualTestCars[0] = null;

        // THEN
        Car[] expectedTestCarsAfterModification = actualTestArrayCarDAO.getCars();

        assertThat(expectedTestCarsAfterModification[0])
                .as("The element at index 0 in actualTestArrayCarDAO state should not be null")
                .isNotNull();

    }

    @Test
    void updateCarCanThrowCarNotFoundExceptionForCarNotFoundWhenRegistrationDoesntExist(){

        // GIVEN expectedNotFoundRegistrationNumber
        String expectedNotFoundRegistrationNumber = "123_6";

        // WHEN
        Car expectedCarNotFound = new Car(
                expectedNotFoundRegistrationNumber,
                new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC);

        // THEN
        assertThatThrownBy(() -> actualTestArrayCarDAO.updateCar(expectedCarNotFound))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessageContaining(expectedCarNotFound.getRegistrationNumber());

    }

}
