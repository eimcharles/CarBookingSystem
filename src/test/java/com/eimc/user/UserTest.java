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

    // The result we are testing (ACTUAL)
    private User actualTestUser;

    // The data we use to compare (EXPECTED)
    private UUID expectedUserId;
    private String expectedFirstName;
    private String expectedLastName;


    @BeforeEach
    void setUp(){

        // GIVEN: Set up the expected data
        expectedUserId = UUID.randomUUID();
        expectedFirstName = "Jerry";
        expectedLastName = "LeBlond";

        // ACT: Construct the actual user
        actualTestUser = new User(expectedUserId, expectedFirstName, expectedLastName);

    }

    @Test
    void constructorShouldCorrectlyInitializeAllFields() {

        // GIVEN & WHEN: Handled in setUp()

        // THEN: Verify the state using AssertJ Fluent API
        assertThat(actualTestUser)
                .as("The User object should be correctly initialized with all fields")
                .isNotNull()
                .returns(expectedUserId, User::getUserId)
                .returns(expectedFirstName, User::getFirstName)
                .returns(expectedLastName, User::getLastName);
    }

    @Test
    void equalsCanCheckEqualityWhenUsersAreIdentical(){

        // GIVEN
        User userWithSameUserId = new User(expectedUserId, expectedFirstName,  expectedLastName);

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with identical attributes are equal")
                .isEqualTo(userWithSameUserId);

    }

    @Test
    void equalsCanCheckEqualityWhenUserIdAreIdentical(){

        // GIVEN
        User differentUserIdSameUserId = new User(expectedUserId, "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with identical userId attributes are equal, even if other attributes are not identical")
                .isEqualTo(differentUserIdSameUserId);

    }

    @Test
    void equalsCanCheckEqualityWhenComparingAUserToItself(){

        // WHEN & THEN
        assertThat(actualTestUser).as("Identical user are equal to each other")
                .isEqualTo(actualTestUser);

    }

    @Test
    void equalsCanCheckInequalityWhenAUserIsNull(){

        // GIVEN
        User nullUser = null;

        // WHEN & THEN
        assertThat(actualTestUser).as("A user is not equal to a null user object ")
                .isNotEqualTo(nullUser);

    }

    @Test
    void equalsCanCheckInequalityWhenUsersAreDifferent(){

        // GIVEN
        User differentUserDifferentId = new User(UUID.randomUUID(), "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();

        // THEN
        assertThat(actualTestUser).as("Users with different userId should not be equal")
                .isNotEqualTo(differentUserDifferentId);

    }

    @Test
    void hashCodeCanCheckEqualityWhenUsersAreIdentical(){

        // GIVEN
        User identicalUser = new User(expectedUserId, expectedFirstName, expectedLastName);

        // WHEN
        int actualUserHashCode = actualTestUser.hashCode();
        int identicalUserHashCode = identicalUser.hashCode();

        // THEN
        assertThat(actualUserHashCode).as("If users are equal, their hash codes must be equal")
                .isEqualTo(identicalUserHashCode);

    }

    @Test
    void hashCodeCanCheckEqualityWhenUserIdsAreIdentical(){

        // GIVEN differentUserSameId
        User differentUserSameId = new User(expectedUserId, "Barry", "LeWhite");

        // WHEN actualTestUser object created in setUp();
        int actualUserHashCode = actualTestUser.hashCode();
        int identicalUserHashCode = differentUserSameId.hashCode();

        // THEN
        assertThat(actualUserHashCode).as("Users with identical userId attributes are equal, even if other attributes are not identical, their hash codes must be equal")
                .isEqualTo(identicalUserHashCode);

    }

}
