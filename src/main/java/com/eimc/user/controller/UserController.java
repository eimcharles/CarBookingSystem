package com.eimc.user.controller;

import com.eimc.common.HttpResponse;
import com.eimc.user.dto.UserRequestDTO;
import com.eimc.user.dto.UserResponseDTO;
import com.eimc.user.model.User;
import com.eimc.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody UserRequestDTO request) {

        User userEntity = request.toEntity();
        User createdUser = userService.createUser(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", UserResponseDTO.from(createdUser)))
                        .message("User created successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.POST.name())
                        .build()
        );

    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        /// TODO: Implement Logic
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId) {
        ///  TODO: Implement Logic
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
