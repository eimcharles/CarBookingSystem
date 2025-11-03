
/**
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import User.User;
import User.UserDAO;
import User.UserService;

import java.util.Arrays;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        System.out.println(Arrays.toString(userService.getUserDAO()));

        User testUser = userService.getUsersByIdFromDAO(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"));
        System.out.println("User found by id " + testUser.getUserId() + " is " + testUser.getName());
    }
}