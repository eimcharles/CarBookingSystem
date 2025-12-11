package com.eimc.booking;

import com.eimc.car.Brand;
import com.eimc.car.Car;
import com.eimc.car.FuelType;
import com.eimc.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 *     Unit tests for the Booking domain class.
 */

public class BookingTest {

    private UUID testBookingId;
    private User testUser;
    private Car testCar;
    private LocalDateTime testBookingTime;
    private Booking testBooking;

    @BeforeEach
    void setUp() {

        // GIVEN: Initialize all important immutable parameters
        testBookingId = UUID.randomUUID();
        testUser = new User(UUID.randomUUID(), "John", "Jones Junior");
        testCar = new Car("123_5", new BigDecimal("59.00"), Brand.HONDA, FuelType.GASOLINE);
        testBookingTime = LocalDateTime.now();

        // GIVEN: The primary object is instantiated
        testBooking = new Booking(testBookingId, testUser, testCar, testBookingTime);
    }

    @Test
    void constructor_ShouldInitializeAllFinalFieldsCorrectly_AndNotBeCancelled() {

        // GIVEN: All parameters defined in setUp() are passed to the constructor.

        // WHEN: A new Booking object is instantiated

        // THEN: All getter methods must return the expected initial values, and cancellation status must be false
        assertEquals(testBookingId, testBooking.getUserBookingID());
        assertEquals(testUser, testBooking.getUser());
        assertEquals(testCar, testBooking.getCar());
        assertEquals(testBookingTime, testBooking.getBookingTime());
        assertFalse(testBooking.isBookingCancelled(), "A Booking must not be cancelled by default.");
    }

    @Test
    void cancelBooking_ShouldSetIsBookingCancelledToTrue() {

        // GIVEN: A booking that is currently active (not cancelled)
        assertFalse(testBooking.isBookingCancelled());

        // WHEN: The cancelBooking() method is called
        testBooking.cancelBooking();

        // THEN: The isBookingCancelled() getter must return true
        assertTrue(testBooking.isBookingCancelled(), "Calling cancelBooking() must set the status to true.");
    }

    @Test
    void equals_ShouldReturnTrue_AndHashCodesMustMatch_WhenOnlyUserBookingIDIsIdentical() {
        // GIVEN: A second Booking object with the same ID, but different core objects (user, car, time).


        // Core objects (different user, different car, different time).
        User differentTestUser = new User(UUID.randomUUID(), "Larry", "Jones Junior");
        Car testCarCopy = new Car("123_5", new BigDecimal("59.00"), Brand.HONDA, FuelType.GASOLINE);
        LocalDateTime differentTime = LocalDateTime.now().minusDays(1);


        Booking matchingIdBooking = new Booking(testBookingId, differentTestUser, testCarCopy, differentTime);

        // equals() compares the unique, immutable ID - (cancellation) doesn't break equality
        matchingIdBooking.cancelBooking();

        // WHEN: We compare the two bookings using equals()
        // THEN: equals() must return true, AND their hash codes MUST be equal
        assertTrue(testBooking.equals(matchingIdBooking),
                "Bookings should be equal if their userBookingID is the same, regardless of other fields.");
        assertEquals(testBooking.hashCode(), matchingIdBooking.hashCode(),
                "Hash codes must be equal if the Bookings are equal based on userBookingID.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenUserBookingIDDiffers() {

        // GIVEN: A second Booking object with a completely different immutable UUID
        UUID differentId = UUID.randomUUID();
        Booking differentIdBooking = new Booking(differentId, testUser, testCar, testBookingTime);

        // WHEN: We compare the two bookings using equals()
        // THEN: equals() must return false
        assertFalse(testBooking.equals(differentIdBooking),
                "Bookings should not be equal if userBookingID differs.");
    }

    @Test
    void equals_ShouldReturnTrue_WhenComparedToItself() {

        // GIVEN: A Booking object (testBooking)
        // WHEN: We compare the object to itself
        // THEN: equals() must return true (Reflexivity)
        assertTrue(testBooking.equals(testBooking),
                "An Booking must be equal to itself.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenComparedAgainstNull() {

        // GIVEN: A Booking object (testBooking)
        // WHEN: We compare the object against null
        // THEN: equals() must return false
        assertFalse(testBooking.equals(null),
                "Booking should not be equal to null.");
    }
}
