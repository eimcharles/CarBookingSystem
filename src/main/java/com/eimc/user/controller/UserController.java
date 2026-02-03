package com.eimc.user.controller;

import com.eimc.user.dto.UserRequestDTO;
import com.eimc.user.dto.UserResponseDTO;
import com.eimc.user.model.User;
import com.eimc.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        /// TODO: Implement Logic
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        /// TODO: Implement Logic
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId) {
        /// TODO: Implement Logic
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        ///  TODO: Implement Logic
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        /// TODO: Implement Logic
        return null;
    }

}
