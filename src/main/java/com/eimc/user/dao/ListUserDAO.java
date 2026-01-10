package com.eimc.user.dao;

import com.eimc.exception.UserNotFoundException;
import com.eimc.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *      ListUserDAO is a Data Access Object (DAO)
 *      class for managing User objects,
 *      it implements the UserDAO contract.
 */

public class ListUserDAO implements UserDAO {

    private final List<User> userDAO = new ArrayList<>();

    public ListUserDAO() {

        addUser(new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                "Charles",
                "Eimer"));

        addUser(new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                "Jerry",
                "LeBlond"));
    }

    @Override
    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");
        userDAO.add(user);
    }

    @Override
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

    @Override
    public  List<User> getUsers() {
        /// Return a new list containing the same users.
        return new ArrayList<>(this.userDAO);
    }
}
