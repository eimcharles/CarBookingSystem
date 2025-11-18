import User.User;
import User.UserService;

/**
 *      Driver class for Car Booking System CLI
 *
 *      TODO: Implement Driver Class
 * */

public class CarBookingMenu {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        displayAllUsers(configuration.getUserService());

    }




    /**
     *          Retrieves all registered
     *          users from the UserService
     *          and prints them to the console.
     *
     *          If no users are found,
     *          a corresponding message is displayed.
     *
     *          @param userService
     *
     *          The UserService instance
     *          used to fetch the data.
     */

    public static void displayAllUsers(UserService userService){

        User[] users = userService.getAllUsers();

        if (users == null || users.length == 0){
            System.out.println("No users registered in the system");
        }

        for (User user : users) {
            System.out.println(user);
        }
    }
}
