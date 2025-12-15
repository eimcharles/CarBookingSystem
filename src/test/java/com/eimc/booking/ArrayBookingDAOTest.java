package com.eimc.booking;

import com.eimc.booking.dao.ArrayBookingDAO;
import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.exception.BookingNotFoundException;
import com.eimc.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *      Unit tests for the ArrayBookingDAO class
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class ArrayBookingDAOTest {

    private ArrayBookingDAO actualTestArrayBookingDAO;

    private Booking actualTestBookingOne;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestArrayBookingDAO = new ArrayBookingDAO();

        actualTestBookingOne = new Booking(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

    }

    @Test
    void getBookingCanReturnBookingsAndHasCorrectSizeAndContent(){

        // WHEN
        Booking[] actualTestBookings = actualTestArrayBookingDAO.getBookings();

        // THEN
        assertThat(actualTestBookings)
                .as("The getBookings() method must return an array of 3 booking with the correct contents.")
                .isNotNull()
                .hasSize(3)
                .containsExactly(actualTestBookingOne, null, null);

    }

    @Test
    void getBookingsCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestBookings
        Booking[] actualTestBookings = actualTestArrayBookingDAO.getBookings();

        // WHEN
        actualTestBookings[0] = null;

        // THEN
        Booking[] expectedTestBookingAfterModification = actualTestArrayBookingDAO.getBookings();

        assertThat(expectedTestBookingAfterModification[0])
                .as("The element at index 0 in actualTestArrayBookingDAO state should not be null")
                .isNotNull();

    }

    @Test
    void updateBookingCanThrowBookingNotFoundExceptionForBookingNotFoundWhenRegistrationNumberDoesntExist(){

        // GIVEN expectedNotFoundRegistrationNumber
        String expectedNotFoundRegistrationNumber = "123_6";

        // WHEN
        Booking expectedBookingNotFound = new Booking(UUID.randomUUID(),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

        /**
         *     Functional Programming:
         *
         *     Asserts that calling updateBooking() with a non-existent booking (within the lambda)
         *     throws a BookingNotFoundException, and verifies the exception message contains the missing
         *     registration number.
         * */

        // THEN
        assertThatThrownBy(() -> actualTestArrayBookingDAO.updateBooking(expectedBookingNotFound))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(expectedBookingNotFound.getUserBookingID().toString());

    }

    @Test
    void getBookingByIdCanThrowBookingNotFoundExceptionForBookingNotFoundWhenBookingIdDoesntExist(){

        // GIVEN expectedNotFoundRandomTestTargetId
        UUID expectedNotFoundRandomTestTargetId = UUID.randomUUID();

        /**
         *     Functional Programming:
         *
         *     Asserts that calling getBookingById() with a non-existent booking id (within the lambda)
         *     throws a BookingNotFoundException, and verifies the exception message contains the missing id.
         * */

        // THEN
        assertThatThrownBy(() -> actualTestArrayBookingDAO.getBookingById(expectedNotFoundRandomTestTargetId))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(expectedNotFoundRandomTestTargetId.toString());

    }
}
