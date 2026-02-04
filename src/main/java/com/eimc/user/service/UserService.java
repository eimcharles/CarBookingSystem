package com.eimc.user.service;

import com.eimc.user.exception.UserNotFoundException;
import com.eimc.user.model.User;
import com.eimc.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return this.userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

    }

    @Transactional
    public void updateUser(){
        ///  TODO: Implement Logic
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        if (!userRepository.existsByUserId(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteByUserId(userId);
    }

}
