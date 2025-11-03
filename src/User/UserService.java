package User;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 *
 *      TODO: Implement user service business logic
 */

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
