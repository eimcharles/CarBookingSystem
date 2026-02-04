package com.eimc.user.controller;

import com.eimc.common.domain.HttpResponse;
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
    public ResponseEntity<HttpResponse> getUsers() {

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("users" ,userService.getUsers()
                                .stream()
                                .map(UserResponseDTO::from)
                                .toList()))
                        .message("Users retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.GET.name())
                        .build()
        );

    }

    @GetMapping("/{userId}")
    public ResponseEntity<HttpResponse> getUserById(@PathVariable UUID userId) {

        User user = userService.getUserById(userId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", UserResponseDTO.from(user)))
                        .message("User retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.GET.name())
                        .build()
        );

    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        ///  TODO: Implement Logic
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpResponse> deleteUserById(@PathVariable UUID userId) {

        userService.deleteUserById(userId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("User deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.DELETE.name())
                        .build()
        );

    }

}
