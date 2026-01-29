package com.eimc.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *      Service class for managing User objects.
 *      Contains business logic related to users.
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID userId) {
        return this.userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
