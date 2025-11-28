package user;

import java.util.Arrays;
import java.util.UUID;

/**
 *      ArrayUserDAO is a Data Access Object (DAO)
 *      class for managing User objects,
 *      it implements the IUserDAO contract.
 *
 *      This implementation stores and manages users using arrays.
 */

public class ArrayUserDAO implements UserDAO {

    private final User[] userDAO;

    public ArrayUserDAO() {

        this.userDAO = new User[]

                {

                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jerry", "Leblond"),

        };
    }

    @Override
    public  User[] getUserDAO() {
        // Copy of User objects returned from userDAO
        return Arrays.copyOf(this.userDAO,this.userDAO.length);
    }
}
