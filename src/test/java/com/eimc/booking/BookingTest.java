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

import static org.assertj.core.api.Assertions.assertThat;

/**
 *      Unit tests for the Booking domain class.
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class BookingTest {

    private Booking actualTestBooking;
    private UUID actualTestUserBookingID;
    private User actualTestUser;
    private Car actualTestCar;
    private LocalDateTime actualTestBookingTime;
    private boolean actualTestisBookingActive;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestUserBookingID = UUID.randomUUID();

        actualTestUser = new User(UUID.randomUUID(), "Jerry", "Leblond");

        actualTestCar = new Car("123_1",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        actualTestBookingTime = LocalDateTime.now();

        actualTestBooking = new Booking(actualTestUserBookingID,
                actualTestUser,
                actualTestCar,
                actualTestBookingTime);

    }

    @Test
    void constructorCanInitializeCarObject() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).isNotNull();

    }

    @Test
    void constructorCanInitializeUserBookingID() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("The userBookingID attribute must be initialized correctly by the constructor.")
                .extracting(Booking::getUserBookingID)                  // Extracts the value of the 'getUserBookingID' attribute
                .isEqualTo(actualTestUserBookingID);

    }

    @Test
    void constructorCanInitializeUser() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("The user object must be initialized correctly by the constructor.")
                .extracting(Booking::getUser)                   // Extracts the value of the 'getUser' object
                .isEqualTo(actualTestUser);

    }

    @Test
    void constructorCanInitializeCar() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("The car object must be initialized correctly by the constructor.")
                .extracting(Booking::getCar)                    // Extracts the value of the 'getCar' object
                .isEqualTo(actualTestCar);

    }

    @Test
    void constructorCanInitializeLocalDateTime() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("The local date and time object must be initialized correctly by the constructor.")
                .extracting(Booking::getBookingTime)            // Extracts the value of the 'getBookingTime' object
                .isEqualTo(actualTestBookingTime);

    }

    @Test
    void constructorCanInitializeBookingStatus() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("The isBookingActive attribute must be initialized correctly by the constructor.")
                .extracting(Booking::isBookingActive)           // Extracts the value of the 'isBookingActive' attribute
                .isEqualTo(true);

    }

    @Test
    void equalsCanCheckEqualityWhenAttributesAreIdentical() {

        // GIVEN expectedTestBookingCopy
        Booking expectedTestBookingCopy = new Booking(actualTestUserBookingID,
                actualTestUser,
                actualTestCar,
                actualTestBookingTime);

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("Bookings with identical attributes are equal")
                .isEqualTo(expectedTestBookingCopy);

    }

    @Test
    void equalsCanCheckEqualityWhenBookingIdsAreIdentical() {

        // GIVEN expectedTestBookingWithIdenticalBookingId
        Booking expectedTestBookingWithIdenticalBookingId = new Booking(actualTestUserBookingID,

                new User(UUID.randomUUID(), "Barry", "LeWhite"),

                new Car("123_2",
                        new BigDecimal("79.00"),
                        Brand.BMW,
                        FuelType.GASOLINE),

                LocalDateTime.now());

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("Bookings with identical userBookingID are equal, even if other attributes are not identical")
                .isEqualTo(expectedTestBookingWithIdenticalBookingId);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingDifferentBookingIds() {

        // GIVEN expectedTestBookingWithDifferentBookingId
        Booking expectedTestBookingWithDifferentBookingId = new Booking(UUID.randomUUID(),
                actualTestUser,
                actualTestCar,
                actualTestBookingTime);

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("Bookings with different userBookingID are not equal, even if other attributes are identical")
                .isNotEqualTo(expectedTestBookingWithDifferentBookingId);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingABookingToItself() {

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("Identical bookings are equal to each other")
                .isEqualTo(actualTestBooking);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingABookingToNull() {

        // GIVEN expectedTestNullBooking
        Booking expectedTestNullBooking = null;

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("A booking is not equal to a null booking object")
                .isNotEqualTo(expectedTestNullBooking);

    }

    @Test
    void hashCodeCanCheckEqualityWhenAttributesAreIdentical() {

        // GIVEN expectedTestBookingCopy
        Booking expectedTestBookingCopy = new Booking(actualTestUserBookingID,
                actualTestUser,
                actualTestCar,
                actualTestBookingTime);

        // WHEN actualTestBooking object created in setUp();
        int actualTestBookingHashCode = actualTestBooking.hashCode();
        int expectedTestBookingHashCode = expectedTestBookingCopy.hashCode();

        // THEN
        assertThat(actualTestBookingHashCode).as("If bookings are equal, their hash codes must be equal")
                .isEqualTo(expectedTestBookingHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenBookingIdsAreIdentical() {

        // GIVEN expectedTestBookingWithIdenticalBookingId
        Booking expectedTestBookingWithIdenticalBookingId = new Booking(actualTestUserBookingID,

                new User(UUID.randomUUID(), "Barry", "LeWhite"),

                new Car("123_2",
                        new BigDecimal("79.00"),
                        Brand.BMW,
                        FuelType.GASOLINE),

                LocalDateTime.now());

        // WHEN actualTestBooking object created in setUp();
        int actualTestBookingHashCode = actualTestBooking.hashCode();
        int expectedTestBookingHashCode = expectedTestBookingWithIdenticalBookingId.hashCode();

        // THEN
        assertThat(actualTestBookingHashCode).as("If bookings are equal by userBookingID, even if their attributes are different, then their hash codes must be equal")
                .isEqualTo(expectedTestBookingHashCode);

    }

    @Test
    void hashCodeCanCheckInequalityWhenComparingDifferentBookingIds() {

        // GIVEN expectedTestBookingWithIdenticalBookingId
        Booking expectedTestBookingWithDifferentBookingId = new Booking(UUID.randomUUID(),
                actualTestUser,
                actualTestCar,
                actualTestBookingTime);


        // WHEN actualTestBooking object created in setUp();
        int actualTestBookingHashCode = actualTestBooking.hashCode();
        int expectedTestBookingHashCode = expectedTestBookingWithDifferentBookingId.hashCode();

        // THEN
        assertThat(actualTestBookingHashCode).as("If bookings are not equal by userBookingID, even if their attributes are identical, then their hash codes must be different")
                .isNotEqualTo(expectedTestBookingHashCode);

    }

    @Test
    void cancelBookingCanUpdateIsBookingActiveStatus() {

        // GIVEN: actualTestBooking object created in setUp();

        // WHEN
        actualTestBooking.cancelBooking();

        // THEN
        assertThat(actualTestBooking.isBookingActive()).as("The cancelBooking() method should change the isBookingActive state to false.")
                .isFalse();

    }

}
