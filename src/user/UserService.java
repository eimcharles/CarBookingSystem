package user;

import exception.UserNotFoundException;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 *
 *      TODO : implement User service business logic
 *      TODO : add comments for methods in UserService
 */

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User[] getAllUsers() {
        // Passing the copy of User objects returned from DAO layer
        return this.userDAO.getUserDAO();
    }

    public User getUsersById(UUID id) {
        for (User user: getAllUsers()){
            if (user.getUserId().equals(id)){
                return user;
            }
        }

        throw new UserNotFoundException(id);

    }
}
