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

    private User expectedTestUserCharles;
    private User expectedTestUserJerry;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestArrayUserDAO = new ArrayUserDAO();

        expectedTestUserCharles = new User(
                UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                "Charles",
                "Eimer");

        expectedTestUserJerry = new User(
                UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                "Jerry",
                "LeBlond");

    }

    @Test
    void getUsersCanReturnUsersAndHasCorrectSizeAndContent(){

        // GIVEN actualTestArrayUserDAO object created in setUp();

        // WHEN
        User[] actualTestUsers = actualTestArrayUserDAO.getUsers();

        // THEN
        assertThat(actualTestUsers).as("The getUsers() method must return an array of 2 users with the correct contents.")
                .isNotNull()
                .hasSize(2)
                .containsExactly(expectedTestUserCharles, expectedTestUserJerry);

    }

    @Test
    void getUsersCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestArrayUserDAO object created in setUp();

        // WHEN actualTestUsers
        User[] actualTestUsers = actualTestArrayUserDAO.getUsers();
        actualTestUsers[0] = null;
        User[] actualTestUsersAfterModification = actualTestArrayUserDAO.getUsers();

        // THEN
        assertThat(actualTestUsersAfterModification[0])
                .as("The element at index 0 in actualTestUsers state should not be null")
                .isNotNull();

    }

    @Test
    void getUserByIdCanReturnCorrespondingUserById(){

        // GIVEN
        UUID testTargetId = expectedTestUserCharles.getUserId();

        // WHEN
        User actualUserReturnedById = actualTestArrayUserDAO.getUserById(testTargetId);

        // THEN
        assertThat(actualUserReturnedById).as("The getUserById() method must return a user with the correct user id.")
                .isNotNull()
                .isEqualTo(expectedTestUserCharles);

    }

    @Test
    void getUserByIdCanThrowUserNotFoundExceptionWhenUserIdDoesntExist(){

        // GIVEN
        UUID nonExistentId = UUID.randomUUID();

        /**
         *     Functional Programming:
         *
         *     Asserts that calling getUserById() with a non-existent ID (within the lambda)
         *     throws a UserNotFoundException, and verifies the exception message contains the missing ID.
         * */

        // WHEN & THEN
        assertThatThrownBy(() -> actualTestArrayUserDAO.getUserById(nonExistentId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(nonExistentId.toString());

    }

}
