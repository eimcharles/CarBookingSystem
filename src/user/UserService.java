package user;

import exception.UserNotFoundException;
import user.dao.ArrayUserDAO;

import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 *
 *      TODO : implement User service business logic
 *      TODO : add comments for methods in UserService
 */

public class UserService {

    private final ArrayUserDAO arrayUserDAO;

    public UserService(ArrayUserDAO arrayUserDAO) {
        this.arrayUserDAO = arrayUserDAO;
    }

    public User[] getAllUsers() {
        // Passing the copy of User objects returned from DAO layer
        return this.arrayUserDAO.getUserDAO();
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
