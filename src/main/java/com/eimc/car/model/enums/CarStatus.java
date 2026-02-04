package com.eimc.car.model.enums;

public enum CarStatus {

    AVAILABLE(false),
    BOOKED(true);

    private final boolean isBooked;

    CarStatus(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

}
