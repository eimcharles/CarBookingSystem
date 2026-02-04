package com.eimc.user.dto;

import com.eimc.user.model.User;
import com.eimc.user.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO (

        UUID userId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        UserRole userRole,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime createdAt

) {

    public static UserResponseDTO from(User user) {

        return new UserResponseDTO(

                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserRole(),
                user.getCreatedAt()

        );
    }

}
