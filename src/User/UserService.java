package User;

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

    public User[] getUsersDAO() {
        return UserDAO.getUserDAO();
    }

    public User getUsersByIdFromDAO(UUID id) {
        for (User user: getUsersDAO()){
            if (user.getUserId().equals(id)){
                return user;
            }
        }
        throw new IllegalStateException(String.format("User with id %s not found", id));
    }
}
