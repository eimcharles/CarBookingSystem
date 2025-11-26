package exception;

/**
 *      CarUnavailableException is a
 *      custom exception thrown when a
 *      request is made for a Car that
 *      is currently booked by another user
 *
 * */

public class CarUnavailableException extends RuntimeException {

    public CarUnavailableException(String registrationNumber) {
        super(String.format("Car with registration number '%s' is currently booked and unavailable.", registrationNumber));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

