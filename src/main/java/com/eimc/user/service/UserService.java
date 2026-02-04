package com.eimc.user.service;

import com.eimc.user.exception.PasswordMismatchException;
import com.eimc.user.exception.UserNotFoundException;
import com.eimc.user.model.User;
import com.eimc.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        ///  TODO: Hash with Spring Security
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
    public User updateUserById(UUID userId, User user){
        return userRepository.findByUserId(userId)
                .map(existingUser -> {

                    /**
                     *      Performs partial or full updates on the user entity.
                     *
                     *      Only updates the fields that are present in the request
                     *      and not blank:
                     *
                     *      - Check if the field is present in the JSON object,
                     *      - Ensure the value to be updated is not an empty String or blank,
                     *      - if both conditions are met, update the given field
                     * */

                    Optional.ofNullable(user.getFirstName()).filter(s -> !s.isBlank())
                            .ifPresent(existingUser::setFirstName);

                    Optional.ofNullable(user.getLastName()).filter(s -> !s.isBlank())
                            .ifPresent(existingUser::setLastName);

                    Optional.ofNullable(user.getEmail()).filter(s -> !s.isBlank())
                            .ifPresent(existingUser::setEmail);

                    Optional.ofNullable(user.getPhoneNumber()).filter(s -> !s.isBlank())
                            .ifPresent(existingUser::setPhoneNumber);

                    ///  @Transactional manages the entity, to remove.
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public void updatePassword(UUID userId, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!user.getPassword().equals(oldPassword)) {
            throw new PasswordMismatchException(userId);
        }

        ///  TODO: re-hash with Spring Security
        user.setPassword(newPassword);
        ///  @Transactional manages the entity, to remove.
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
    }

}
