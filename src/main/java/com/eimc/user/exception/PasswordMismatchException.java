package com.eimc.user.exception;

import java.util.UUID;

public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(UUID userId) {
        super(String.format("Current password does not match for user: %s", userId));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
