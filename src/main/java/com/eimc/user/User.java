package com.eimc.user;

import java.util.Objects;
import java.util.UUID;

/**
 *      Domain class for User Object
 *
 *      TODO fix User entity model for Spring Boot
 *
 * */

public class User {

    private UUID userId;
    private String firstName;
    private String lastName;

    public User(UUID userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User { userId = %s , name = '%s', lastName = '%s'}".formatted(userId, firstName, lastName);
    }
}
