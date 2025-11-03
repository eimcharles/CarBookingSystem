
/*
*      Driver class for Car Booking System CLI
*
*      TODO: Write comment for driver class
*      TODO: Implement Driver Class
* */

import User.User;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        User testUser = new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer");

        System.out.println(testUser);

    }
}