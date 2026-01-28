package com.eimc.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *      Unit tests for the ListUserDAO class
 *
 *      Test methods follow the Arrange-Act-Assert (AAA) pattern,
 *      commonly labeled as Given-When-Then:
 *
 *      1. GIVEN (Arrange): Set up the required inputs, mocks, and expected outcomes.
 *      2. WHEN (Act): Execute the method under test.
 *      3. THEN (Assert): Verify the result using assertions (AssertJ).
 */

public class UserRepositoryTest {

    private UserRepository actualTestUserRepository;

    private User expectedTestUserCharles;
    private User expectedTestUserJerry;

    @BeforeEach
    void setUp(){

        // GIVEN
        actualTestUserRepository = new UserRepository();

        expectedTestUserJerry = new User(
                UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                "Jerry",
                "LeBlond");

    }

    @Test
    void getUsersCanReturnADefensiveCopyAndExternalModificationDoesNotAffectInternalState(){

        // GIVEN actualTestListUserDAO object created in setUp();

        // WHEN actualTestUsers
        List<User> actualTestUsers = actualTestUserRepository.getUsers();
        actualTestUsers.set(0, null);
        List<User> actualTestUsersAfterModification = actualTestUserRepository.getUsers();

        // THEN
        assertThat(actualTestUsersAfterModification.get(0))
                .as("The element at index 0 in actualTestUsers state should not be null")
                .isNotNull();

    }

    @Test
    void getUserByIdCanReturnCorrespondingUserById(){

        // GIVEN
        UUID testTargetId = expectedTestUserJerry.getUserId();

        // WHEN
        User actualUserReturnedById = actualTestUserRepository.getUserById(testTargetId);

        // THEN
        assertThat(actualUserReturnedById).as("The getUserById() method must return a user with the correct user id.")
                .isNotNull()
                .isEqualTo(expectedTestUserJerry);

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
        assertThatThrownBy(() -> actualTestUserRepository.getUserById(nonExistentId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(nonExistentId.toString());

    }

}
