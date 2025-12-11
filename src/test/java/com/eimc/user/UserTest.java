package com.eimc.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 *     Unit tests for the User domain class.
 */

public class UserTest {

    private UUID testId;
    private User testUser;

    @BeforeEach
    void setUp() {


        // GIVEN: Initialize important parameters
        testId = UUID.randomUUID();

        // GIVEN: The primary object is instantiated
        testUser = new User(testId, "John", "MacDougal");
    }

    @Test
    void constructor_ShouldInitializeUserIdCorrectly() {
        assertEquals(testId, testUser.getUserId(),
                "The userId must be initialized correctly by the constructor.");

    }

    @Test
    void equals_ShouldReturnTrue_AndHashCodesMustMatch_WhenAllFieldsAreIdentical() {

        // GIVEN: A second user object (testUserCopy) with fields identical to testUser
        User testUserCopy = new User(testId, "John", "MacDougal");

        // WHEN: We compare testUser with testUserCopy using equals()
        // THEN: equals() must return true, AND their hash codes must be equal
        assertTrue(testUser.equals(testUserCopy),
                "Users with identical fields are considered equal.");

        assertEquals(testUser.hashCode(), testUserCopy.hashCode(),
                "If Users are equal, their hash codes are equal.");
    }

    @Test
    void equals_ShouldReturnTrue_WhenComparedToItself() {

        // GIVEN: A User object (testUser)
        // WHEN: We compare the object to itself
        // THEN: equals() must return true (Reflexivity)
        assertTrue(testUser.equals(testUser),
                "A User must be equal to itself.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenUserIdDiffers() {

        // GIVEN: A second user object (differentTestUser) with a distinct UUID
        User differentTestUser = new User(UUID.randomUUID(), "Larry", "Kane");

        // WHEN: We compare testUser with differentTestUser
        // THEN: equals() must return false
        assertFalse(testUser.equals(differentTestUser),
                "Users should not be equal if their userId differs.");
    }

    @Test
    void equals_ShouldReturnFalse_WhenComparedAgainstNull() {

        // GIVEN: A user object (testUser)
        // WHEN: We compare the object against null
        // THEN: equals() must return false
        assertFalse(testUser.equals(null),
                "Users should not be equal to null.");
    }
}
