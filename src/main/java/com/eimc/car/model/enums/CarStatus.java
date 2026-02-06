package com.eimc.car.model.enums;

public enum CarStatus {

    /// Available for booking
    AVAILABLE(false),

    /// Reserved online and ready for pickup
    RESERVED(true),

    /// Booked and picked up
    BOOKED(true);

    private final boolean isBooked;

    CarStatus(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

}
