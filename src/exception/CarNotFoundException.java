package exception;

/**
 *      CarNotFoundException is a
 *      custom exception thrown when a
 *      request is made for a Car that does not exist
 *      in the system
 * */

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String registrationNumber) {
        super(String.format("Car with registration number '%s' not found.", registrationNumber));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
