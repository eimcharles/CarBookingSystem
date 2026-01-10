package com.eimc.user.dao;

import com.eimc.user.User;

import java.util.List;
import java.util.UUID;

/**
 *      UserDAO defines the contract for all data
 *      access operations related to User entities
 * */

public interface UserDAO {

    void addUser(User user);

    User getUserById(UUID id);

    List<User> getUsers();

}
