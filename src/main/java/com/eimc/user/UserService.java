package com.eimc.user;

import com.eimc.user.dao.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 *
 *      TODO fix User Service business logic for Spring Boot
 *
 */

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID id) {
        return this.userRepository.getUserById(id);
    }

    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

}
