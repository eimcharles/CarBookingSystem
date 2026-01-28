package com.eimc.booking;

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

public class BookingRepositoryTest {

    private BookingRepository actualTestBookingRepository;

    private Booking expectedTestBookingOne;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestBookingRepository = new BookingRepository();

        expectedTestBookingOne = new Booking(UUID.fromString("8e397f1e-e7a4-4c39-8331-968a9ab3faef"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "LeBlond"),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                LocalDateTime.now());

    }


    @Test
    void removeBookingCanThrowIllegalStateExceptionForActiveBookings(){

        // GIVEN
        actualTestBookingRepository.addBooking(expectedTestBookingOne);

        // WHEN
        assertThatThrownBy(() -> actualTestBookingRepository.removeBooking(expectedTestBookingOne))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(expectedTestBookingOne.getUserBookingID().toString());

        // THEN
        assertThat(actualTestBookingRepository.getBookingById(expectedTestBookingOne.getUserBookingID()))
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
        assertThatThrownBy(() -> actualTestBookingRepository.removeBooking(bookingNotInDAO))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(bookingNotInDAO.getUserBookingID().toString());

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
        assertThatThrownBy(() -> actualTestBookingRepository.getBookingById(nonExistentId))
                .isInstanceOf(BookingNotFoundException.class)
                .hasMessageContaining(nonExistentId.toString());

    }

}
