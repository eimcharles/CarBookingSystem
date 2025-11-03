
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

        User testUser1 = new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer");
        User testUser2 = new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Charles", "Eimer");
        User testUser3 = new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Larry", "Eimer");

        System.out.println(testUser1);
        System.out.println(testUser1.equals(testUser2));
        System.out.println(testUser1.equals(testUser3));

    }
}