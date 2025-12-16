package com.eimc.user;

import com.eimc.exception.UserNotFoundException;
import com.eimc.user.dao.ArrayUserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *      Unit tests for the ArrayUserDAO class
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class ArrayUserDAOTest {

    private ArrayUserDAO actualTestArrayUserDAO;

    private User actualTestUserCharles;
    private User actualTestUserJerry;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestArrayUserDAO = new ArrayUserDAO();

        actualTestUserCharles = new User(
                UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                "Charles",
                "Eimer");

        actualTestUserJerry = new User(
                UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                "Jerry",
                "Leblond");

    }

    @Test
    void getUsersCanReturnUsersAndHasCorrectSizeAndContent(){

        // GIVEN actualTestArrayUserDAO object created in setUp();

        // WHEN
        User[] expectedTestUsers = actualTestArrayUserDAO.getUsers();

        // THEN
        assertThat(expectedTestUsers).as("The getUsers() method must return an array of 2 users with the correct contents.")
                .isNotNull()
                .hasSize(2)
                .containsExactly(actualTestUserCharles, actualTestUserJerry);

    }

    @Test
    void getUserCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestArrayUserDAO object created in setUp();

        // WHEN expectedTestUsers
        User[] expectedTestUsers = actualTestArrayUserDAO.getUsers();
        expectedTestUsers[0] = null;

        // THEN
        User[] expectedTestUsersAfterModification = actualTestArrayUserDAO.getUsers();

        assertThat(expectedTestUsersAfterModification[0])
                .as("The element at index 0 in expectedTestUsers state should not be null")
                .isNotNull();

    }

    @Test
    void getUserByIdCanReturnCorrespondingUserById(){

        // GIVEN actualTestTargetId
        UUID actualTestTargetId = actualTestUserCharles.getUserId();

        // WHEN
        User expectedTestUserReturnedById = actualTestArrayUserDAO.getUserById(actualTestTargetId);

        // THEN
        assertThat(actualTestUserCharles).as("The getUserById() method must return a user with the correct user id.")
                .isNotNull()
                .isEqualTo(expectedTestUserReturnedById);

    }

    @Test
    void getUserByIdCanThrowUserNotFoundExceptionWhenUserIdDoesntExist(){

        // GIVEN actualTestArrayUserDAO object created in setUp();

        // WHEN expectedNotFoundRandomTestTargetId
        UUID expectedNotFoundRandomTestTargetId = UUID.randomUUID();

        /**
         *     Functional Programming:
         *
         *     Asserts that calling getUserById() with a non-existent ID (within the lambda)
         *     throws a UserNotFoundException, and verifies the exception message contains the missing ID.
         * */

        // THEN
        assertThatThrownBy(() -> actualTestArrayUserDAO.getUserById(expectedNotFoundRandomTestTargetId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(expectedNotFoundRandomTestTargetId.toString());

    }

}
