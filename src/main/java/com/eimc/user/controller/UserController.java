package com.eimc.user.controller;

import com.eimc.common.domain.HttpResponse;
import com.eimc.user.dto.UserPasswordUpdateDTO;
import com.eimc.user.dto.UserRequestDTO;
import com.eimc.user.dto.UserResponseDTO;
import com.eimc.user.dto.UserUpdateDTO;
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

    ///  TODO add @Valid notation for UserRequestDTO payload
    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody UserRequestDTO request) {

        User user = request.toEntity();
        User createdUser = userService.createUser(user);

        /// TODO add a location header for newly created resource

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
                        .data(Map.of("users" , userService.getUsers()
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

    ///  TODO add @Valid notation for UserUpdateDTO payload
    @PatchMapping("/{userId}")
    public ResponseEntity<HttpResponse> updateUserById(
            @PathVariable UUID userId,
            @RequestBody UserUpdateDTO request) {

        User user = request.toEntity();
        User updatedUser = userService.updateUserById(userId, user);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", UserResponseDTO.from(updatedUser)))
                        .message("User updated successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.PATCH.name())
                        .build()
        );

    }

    ///  TODO add @Valid notation for UserPasswordUpdateDTO payload
    @PatchMapping("/{userId}/password")
    public ResponseEntity<HttpResponse> updatePasswordById(
            @PathVariable UUID userId,
            @RequestBody UserPasswordUpdateDTO request) {

        userService.updatePassword(userId, request.oldPassword(), request.newPassword());

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Password updated successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.PATCH.name())
                        .build()
        );
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
