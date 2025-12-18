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

    // The result we are testing (ACTUAL)
    private Booking actualTestBooking;

    // The data we use to compare (EXPECTED)
    private UUID expectedUserBookingID;
    private User expectedUser;
    private Car expectedCar;
    private LocalDateTime expectedBookingTime;

    @BeforeEach
    void setUp(){

        // GIVEN: Set up the expected data
        expectedUserBookingID = UUID.randomUUID();

        expectedUser = new User(UUID.randomUUID(), "Jerry", "LeBlond");

        expectedCar = new Car("123_1",
                new BigDecimal("89.00"),
                Brand.BMW,
                FuelType.ELECTRIC);

        expectedBookingTime = LocalDateTime.now();

        // ACT: Construct the actual booking
        actualTestBooking = new Booking(expectedUserBookingID,
                expectedUser,
                expectedCar,
                expectedBookingTime);

    }

    @Test
    void constructorCanCorrectlyInitializeAllFields() {
        // GIVEN & WHEN handled in setUp()

        // THEN
        assertThat(actualTestBooking)
                .as("The Booking object should correctly initialized")
                .isNotNull()
                .returns(expectedUserBookingID, Booking::getUserBookingID)
                .returns(expectedUser, Booking::getUser)
                .returns(expectedCar, Booking::getCar)
                .returns(expectedBookingTime, Booking::getBookingTime)
                .returns(true, Booking::isBookingActive);
    }

    @Test
    void equalsCanCheckEqualityWhenBookingsAreIdentical() {

        // GIVEN
        Booking identicalBooking = new Booking(expectedUserBookingID,
                expectedUser,
                expectedCar,
                expectedBookingTime);

        // WHEN actualTestBooking object created in setUp();

        // THEN
        assertThat(actualTestBooking).as("Bookings with identical attributes are equal")
                .isEqualTo(identicalBooking);

    }

    @Test
    void equalsCanCheckEqualityWhenBookingIdsAreIdentical() {

        // GIVEN
        Booking bookingWithIdenticalBookingId = new Booking(expectedUserBookingID,

                new User(UUID.randomUUID(), "Barry", "LeWhite"),

                new Car("123_2",
                        new BigDecimal("79.00"),
                        Brand.BMW,
                        FuelType.GASOLINE),

                LocalDateTime.now());

        // WHEN & THEN
        assertThat(actualTestBooking).as("Bookings with identical userBookingID are equal, even if other attributes are not identical")
                .isEqualTo(bookingWithIdenticalBookingId);

    }

    @Test
    void equalsCanCheckInequalityWhenBookingIdsAreDifferent() {

        // GIVEN
        Booking bookingWithDifferentBookingId = new Booking(UUID.randomUUID(),
                expectedUser,
                expectedCar,
                expectedBookingTime);

        // WHEN & THEN
        assertThat(actualTestBooking).as("Bookings with different userBookingID are not equal, even if other attributes are identical")
                .isNotEqualTo(bookingWithDifferentBookingId);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingABookingToItself() {

        // WHEN & THEN
        assertThat(actualTestBooking).as("Identical bookings are equal to each other")
                .isEqualTo(actualTestBooking);

    }

    @Test
    void equalsCanCheckInequalityWhenABookingIsNull() {

        // GIVEN
        Booking nullBooking = null;

        // WHEN & THEN
        assertThat(actualTestBooking).as("A booking is not equal to a null booking object")
                .isNotEqualTo(nullBooking);

    }

    @Test
    void hashCodeCanCheckEqualityWhenBookingsAreIdentical() {

        // GIVEN
        Booking identicalBooking = new Booking(expectedUserBookingID,
                expectedUser,
                expectedCar,
                expectedBookingTime);

        // WHEN
        int actualBookingHashCode = actualTestBooking.hashCode();
        int identicalBookingHashCode = identicalBooking.hashCode();

        // THEN
        assertThat(actualBookingHashCode).as("If bookings are equal, their hash codes must be equal")
                .isEqualTo(identicalBookingHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenBookingIdsAreIdentical() {

        // GIVEN
        Booking bookingWithIdenticalBookingId = new Booking(expectedUserBookingID,

                new User(UUID.randomUUID(), "Barry", "LeWhite"),

                new Car("123_2",
                        new BigDecimal("79.00"),
                        Brand.BMW,
                        FuelType.GASOLINE),

                LocalDateTime.now());

        // WHEN
        int actualBookingHashCode = actualTestBooking.hashCode();
        int differentBookingHashCode = bookingWithIdenticalBookingId.hashCode();

        // THEN
        assertThat(actualBookingHashCode).as("If bookings are equal by userBookingID, even if their attributes are different, then their hash codes must be equal")
                .isEqualTo(differentBookingHashCode);

    }

    @Test
    void hashCodeCanCheckInequalityWhenBookingIdsAreDifferent() {

        // GIVEN
        Booking bookingWithDifferentBookingId = new Booking(UUID.randomUUID(),
                expectedUser,
                expectedCar,
                expectedBookingTime);


        // WHEN
        int actualBookingHashCode = actualTestBooking.hashCode();
        int differentBookingHashCode = bookingWithDifferentBookingId.hashCode();

        // THEN
        assertThat(actualBookingHashCode).as("If bookings are not equal by userBookingID, even if their attributes are identical, then their hash codes must be different")
                .isNotEqualTo(differentBookingHashCode);

    }

    @Test
    void cancelBookingCanUpdateIsBookingActiveStatus() {

        // GIVEN: A booking is initialized as active

        // WHEN
        actualTestBooking.cancelBooking();

        // THEN
        assertThat(actualTestBooking.isBookingActive()).as("The cancelBooking() method should change the isBookingActive state to false.")
                .isFalse();

    }

}
