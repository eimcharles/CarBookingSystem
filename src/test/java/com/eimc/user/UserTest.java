package com.eimc.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *      Unit tests for the User domain class.
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class UserTest {

    private User actualTestUser;

    private UUID actualTestUserId;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestUserId = UUID.randomUUID();
        actualTestUser = new User(actualTestUserId, "Jerry", "LeBlond");

    }

    @Test
    void constructorCanInitializeUserObject(){

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).isNotNull();

    }

    @Test
    void constructorCanInitializeUserId(){

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser)
                .as("The userId attribute must be initialized correctly by the constructor.")
                .extracting(User::getUserId)          // Extracts the value of the 'userId' attribute
                .isEqualTo(actualTestUserId);

    }

    @Test
    void equalsCanCheckEqualityWhenAttributesAreIdentical(){

        // GIVEN expectedTestUserCopy
        User expectedTestUserCopy = new User(actualTestUserId, "Jerry", "LeBlond");

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with identical attributes are equal")
                .isEqualTo(expectedTestUserCopy);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingAUserToItself(){

        // WHEN expectedTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Identical user are equal to each other")
                .isEqualTo(actualTestUser);

    }

    @Test
    void equalsCanCheckEqualityWhenAttributesAreDifferentButUserIdAreIdentical(){

        // GIVEN expectedDifferentUserId
        User expectedDifferentUserId = new User(actualTestUserId, "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with identical userId attributes are equal, even if other attributes are not identical")
                .isEqualTo(expectedDifferentUserId);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingAUserToDifferentUser(){

        // GIVEN expectedDifferentUser
        User expectedDifferentUser = new User(UUID.randomUUID(), "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with different userId should not be equal")
                .isNotEqualTo(expectedDifferentUser);

    }

    @Test
    void equalsCanCheckInequalityWhenComparingAUserToNull(){

        // GIVEN expectedTestNullUser
        User expectedTestNullUser = null;

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("A user is not equal to a null user object ")
                .isNotEqualTo(expectedTestNullUser);

    }

    @Test
    void hashCodeCanCheckEqualityWhenAttributesAreIdentical(){

        // GIVEN expectedTestUserCopy
        User expectedTestUserCopy = new User(actualTestUserId, "Jerry", "LeBlond");

        // WHEN actualTestUser object created in setUp();
        int actualTestUserHashCode = actualTestUser.hashCode();
        int expectedTestUserCopyHashCode = expectedTestUserCopy.hashCode();

        // THEN
        assertThat(actualTestUserHashCode).as("If users are equal, their hash codes must be equal")
                .isEqualTo(expectedTestUserCopyHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenAttributesAreDifferentButIdsAreIdentical(){

        // GIVEN expectedTestUserCopy
        User expectedTestUserCopy = new User(actualTestUserId, "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();
        int actualTestUserHashCode = actualTestUser.hashCode();
        int expectedTestUserCopyHashCode = expectedTestUserCopy.hashCode();

        // THEN
        assertThat(actualTestUserHashCode).as("Users with identical userId attributes are equal, even if other attributes are not identical, their hash codes must be equal")
                .isEqualTo(expectedTestUserCopyHashCode);

    }

}
