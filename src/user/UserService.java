package user;

import user.dao.ArrayUserDAO;

import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 *
 *      TODO : add comments for methods in UserService
 */

public class UserService {

    private final ArrayUserDAO arrayUserDAO;

    public UserService(ArrayUserDAO arrayUserDAO) {
        this.arrayUserDAO = arrayUserDAO;
    }

    public User[] getUsers() {

        // Get all users from DAO
        User[] users = this.arrayUserDAO.getUsers();

        // If users is null or empty, return empty array
        if (users == null || users.length == 0){
            return new User[0];
        }

        int nonNullUserCount = getNonNullUserCount(users);

        if (nonNullUserCount == 0){
            return new User[0];
        }

        User[] nonNullUsers = populateNonNullUsers(nonNullUserCount, users);

        return nonNullUsers;
    }

    private static User[] populateNonNullUsers(int nonNullUserCount, User[] users) {

        // Create a new array with the nonNullUserCount for size
        User[] nonNullUsers = new User[nonNullUserCount];

        int index = 0;
        for (int i = 0; i < users.length; i++) {

            User nonNullUser = users[i];

            if (nonNullUser != null){
                nonNullUsers[index++] = nonNullUser;
            }
        }
        return nonNullUsers;
    }

    private static int getNonNullUserCount(User[] users) {

        // Count non-null users in DAO array
        int nonNullUserCount = 0;
        for (User user : users) {
            if (user != null){
                nonNullUserCount++;
            }
        }
        return nonNullUserCount;
    }

    public User getUserById(UUID id) {
        return this.arrayUserDAO.getUserById(id);
    }

}
