package user;

import user.dao.ArrayUserDAO;

import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 */

public class UserService {

    private final ArrayUserDAO arrayUserDAO;

    public UserService(ArrayUserDAO arrayUserDAO) {
        this.arrayUserDAO = arrayUserDAO;
    }

    /**
     *
     *
     */

    public User getUserById(UUID id) {
        return this.arrayUserDAO.getUserById(id);
    }

    /**
     *
     *
     */

    public User[] getUsers() {

        // Get all users from DAO
        User[] users = this.arrayUserDAO.getUsers();

        // If users is null or empty, return empty array
        if (users == null || users.length == 0){
            return new User[0];
        }

        // Number of non-null users
        int nonNullUserCount = getUserCount(users);

        if (nonNullUserCount == 0){
            return new User[0];
        }

        // Create a new array with the nonNullUserCount for size
        User[] nonNullUsers = new User[nonNullUserCount];

        // Populates a pre-sized Car array with nonNullUsers
        populateUsers(users, nonNullUsers);

        return nonNullUsers;
    }

    private static int getUserCount(User[] users) {

        // Count non-null users in DAO array
        int nonNullUserCount = 0;
        for (User user : users) {
            if (user != null){
                nonNullUserCount++;
            }
        }
        return nonNullUserCount;
    }

    private static void populateUsers(User[] users, User[] nonNullUsers) {

        // Index for the new array, avoids null gaps,
        int index = 0;

        // Look through users
        for (int i = 0; i < users.length; i++) {

            // Use the user at the current index of the source array
            User nonNullUser = users[i];

            // Add to nonNullUsers
            if (nonNullUser != null){
                nonNullUsers[index++] = nonNullUser;
            }
        }
    }

}
