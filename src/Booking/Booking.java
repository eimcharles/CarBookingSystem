package Booking;

/**
 *      Domain class for Booking Object
 * */

import Car.Car;
import User.User;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {

    private UUID userBookingID;
    private User user;
    private Car car;
    private LocalDateTime bookingTime;

    private boolean isBookingCancelled;

    public Booking(UUID userBookingID, User user, Car car, LocalDateTime bookingTime, boolean isBookingCancelled) {
        this.userBookingID = userBookingID;
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isBookingCancelled = isBookingCancelled;
    }

    public Booking(UUID userBookingID, User user, Car car, LocalDateTime bookingTime){
        this(userBookingID ,user, car, bookingTime, false);
    }

    public UUID getUserBookingID() {
        return userBookingID;
    }

    public void setUserBookingID(UUID userBookingID) {
        this.userBookingID = userBookingID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public boolean isBookingCancelled() {
        return isBookingCancelled;
    }

    public void setBookingCancelled(boolean bookingCancelled) {
        isBookingCancelled = bookingCancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return isBookingCancelled == booking.isBookingCancelled &&
                Objects.equals(userBookingID, booking.userBookingID) &&
                Objects.equals(user, booking.user) && Objects.equals(car, booking.car) &&
                Objects.equals(bookingTime, booking.bookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userBookingID, user, car, bookingTime, isBookingCancelled);
    }

    @Override
    public String toString() {
        return "Booking { userBookingID = %s, user = %s, car = %s, bookingTime = %s, isBookingCancelled = %s }".formatted(userBookingID, user, car, bookingTime, isBookingCancelled);
    }
}
