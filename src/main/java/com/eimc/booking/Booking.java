package com.eimc.booking;

/**
 *      Domain class for Booking Object
 *
 *      TODO fix Booking entity model for Spring Boot
 *
 * */

import com.eimc.car.Car;
import com.eimc.user.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {

    private final UUID userBookingID;
    private final User user;
    private final Car car;
    private final LocalDateTime bookingTime;
    private boolean isBookingActive;

    public Booking(UUID userBookingID, User user, Car car, LocalDateTime bookingTime) {
        this.userBookingID = userBookingID;
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isBookingActive = true;
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

    public boolean isBookingActive() {
        return isBookingActive;
    }

    public void cancelBooking() {
        this.isBookingActive = false;
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
        return "Booking { userBookingID = %s, user = %s, car = %s, bookingTime = %s, isBookingActive = %s }".formatted(userBookingID, user, car, bookingTime, isBookingActive);
    }
}
