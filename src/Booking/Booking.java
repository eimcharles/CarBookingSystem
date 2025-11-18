package Booking;

/**
 *      Domain class for Booking Object
 * */

import Car.Car;
import User.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {

    private final UUID userBookingID;
    private final User user;
    private final Car car;
    private final LocalDateTime bookingTime;
    private boolean isBookingCancelled;

    public Booking(UUID userBookingID, User user, Car car, LocalDateTime bookingTime) {
        this.userBookingID = userBookingID;
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isBookingCancelled = false;
    }

    public UUID getUserBookingID() {
        return userBookingID;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public boolean isBookingCancelled() {
        return isBookingCancelled;
    }

    public void cancelBooking() {
        this.isBookingCancelled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;

        // Compares the unique, immutable ID
        return Objects.equals(userBookingID, booking.userBookingID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userBookingID);
    }

    @Override
    public String toString() {
        return "Booking { userBookingID = %s, user = %s, car = %s, bookingTime = %s, isBookingCancelled = %s }".formatted(userBookingID, user, car, bookingTime, isBookingCancelled);
    }
}
