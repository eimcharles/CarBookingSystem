package com.eimc.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserRepository {

    private final List<User> userDAO = new ArrayList<>();

    public UserRepository() {

        addUser(new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                "Jerry",
                "LeBlond"));
    }

    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");
        userDAO.add(user);
    }

    public User getUserById(UUID id) {
        if (id == null){
            throw new IllegalArgumentException(String.format("userId: %s cannot be null",id));
        }

        ///  Retrieves user with a matching UUID id, throws UserNotFoundException if not found
        return userDAO.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public  List<User> getUsers() {
        /// Return a new list containing the same users.
        return new ArrayList<>(this.userDAO);
    }
}
