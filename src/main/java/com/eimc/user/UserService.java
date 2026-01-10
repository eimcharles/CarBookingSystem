package com.eimc.user;

import com.eimc.user.dao.ListUserDAO;

import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 */

public class UserService {

    private final ListUserDAO listUserDAO;

    public UserService(ListUserDAO listUserDAO) {
        this.listUserDAO = listUserDAO;
    }

    public User getUserById(UUID id) {
        return this.listUserDAO.getUserById(id);
    }

    public List<User> getUsers() {
        return this.listUserDAO.getUsers();
    }

}
