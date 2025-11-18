package User;

import java.util.Arrays;
import java.util.UUID;

/**
 *      Data Access Object (DAO) class for managing User objects.
 *      This implementation stores and manages users using arrays.
 */

public class UserDAO {

    private static final User[] userDAO;

    static {

        userDAO = new User[]{

            new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer"),
            new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "Leblond"),

        };
    }

    public static User[] getUserDAO() {
        // Copy of User objects returned from userDAO
        return Arrays.copyOf(userDAO, userDAO.length);
    }
}
