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

    private Booking expectedTestBookingOne;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestArrayBookingDAO = new ArrayBookingDAO();

        expectedTestBookingOne = new Booking(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

    }

    @Test
    void getBookingsCanReturnBookingsAndHasCorrectSizeAndContent(){

        // GIVEN actualTestArrayBookingDAO object created in setUp();

        // WHEN
        Booking[] actualTestBookings = actualTestArrayBookingDAO.getBookings();

        // THEN
        assertThat(actualTestBookings)
                .as("The getBookings() method must return an array of 3 booking with the correct contents.")
                .isNotNull()
                .hasSize(3)
                .containsExactly(expectedTestBookingOne, null, null);

    }

    @Test
    void getBookingsCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestArrayBookingDAO object created in setUp();

        // WHEN
        Booking[] actualTestBookings = actualTestArrayBookingDAO.getBookings();
        actualTestBookings[0] = null;
        Booking[] actualTestBookingsAfterModification = actualTestArrayBookingDAO.getBookings();

        // THEN
        assertThat(actualTestBookingsAfterModification[0])
                .as("The element at index 0 in the internal state of actualTestArrayBookingDAO state should not be null")
                .isNotNull();

    }

    @Test
    void removeBookingCanSuccessfullyRemoveCanceledBooking() {

        // GIVEN
        Booking[] testBookingsBeforeRemoval = actualTestArrayBookingDAO.getBookings();

        assertThat(testBookingsBeforeRemoval)
                .as("The booking should exist before calling removeBooking()")
                .isNotNull()
                .hasSize(3)
                .containsExactly(expectedTestBookingOne, null, null);

        expectedTestBookingOne.cancelBooking();


        // WHEN
        actualTestArrayBookingDAO.removeBooking(expectedTestBookingOne);

        // THEN
        Booking[] actualTestBookings = actualTestArrayBookingDAO.getBookings();

        assertThat(actualTestBookings)
                .as("The removeBooking() method must remove an existing booking from the array")
                .isNotNull()
                .hasSize(3)
                .containsExactly(null, null, null);

    }

    @Test
    void removeBookingCanThrowIllegalStateExceptionForActiveBookings(){

        // GIVEN
        actualTestArrayBookingDAO.addBooking(expectedTestBookingOne);

        // WHEN
        assertThatThrownBy(() -> actualTestArrayBookingDAO.removeBooking(expectedTestBookingOne))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(expectedTestBookingOne.getUserBookingID().toString());

        // THEN
        assertThat(actualTestArrayBookingDAO.getBookingById(expectedTestBookingOne.getUserBookingID()))
                .isEqualTo(expectedTestBookingOne);
    }

    @Test
    void removeBookingCanThrowBookingNotFoundExceptionForInvalidBookingId(){

        // GIVEN
        Booking bookingNotInDAO = new Booking(UUID.randomUUID(),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_1", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

        bookingNotInDAO.cancelBooking();

        /**
         *     Functional Programming:
         *
         *     Asserts that calling removeBooking() with a non-existent booking (within the lambda)
         *     throws a BookingNotFoundException, and verifies the exception message contains the missing
         *     booking id.
         * */

        // WHEN & THEN
        assertThatThrownBy(() -> actualTestArrayBookingDAO.removeBooking(bookingNotInDAO))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(bookingNotInDAO.getUserBookingID().toString());

    }

    @Test
    void getBookingByIdCanReturnCorrespondingBookingById(){

        // GIVEN
        UUID testTargetId = expectedTestBookingOne.getUserBookingID();

        // WHEN
        Booking actualTestBookingReturnedById = actualTestArrayBookingDAO.getBookingById(testTargetId);

        // THEN
        assertThat(actualTestBookingReturnedById).as("The getBookingById() method must return a booking with the correct booking id.")
                .isNotNull()
                .isEqualTo(expectedTestBookingOne);

    }

    @Test
    void getBookingByIdCanThrowBookingNotFoundExceptionWhenBookingIdDoesntExist(){

        // GIVEN
        UUID nonExistentId = UUID.randomUUID();

        /**
         *     Functional Programming:
         *
         *     Asserts that calling getBookingById() with a non-existent booking id (within the lambda)
         *     throws a BookingNotFoundException, and verifies the exception message contains the missing id.
         * */

        // WHEN & THEN
        assertThatThrownBy(() -> actualTestArrayBookingDAO.getBookingById(nonExistentId))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(nonExistentId.toString());

    }

}
