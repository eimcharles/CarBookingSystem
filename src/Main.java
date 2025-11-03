
/**
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import User.UserDAO;
import User.UserService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        System.out.println(userService.getClass());

    }
}