package User;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 */

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User[] getAllUsers() {
        return this.userDAO.getUserDAO();
    }

    public User getUsersById(UUID id) {
        for (User user: getAllUsers()){
            if (user.getUserId().equals(id)){
                return user;
            }
        }
        throw new NoSuchElementException(String.format("User with id %s not found", id));
    }
}
