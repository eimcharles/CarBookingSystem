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

    public User[] getUserDAO() {
        return UserDAO.getUserDAO();
    }

    public User getUsersByIdFromDAO(UUID id){
        for (User user: getUserDAO()){
            if (user.getUserId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
