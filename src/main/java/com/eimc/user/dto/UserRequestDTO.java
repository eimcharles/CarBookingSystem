package com.eimc.user.dto;

import com.eimc.user.model.User;

public record UserRequestDTO(

        ///  TODO add validation for user payload
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber

) {

    public User toEntity() {
        User user = new User();

        user.setFirstName(this.firstName());
        user.setLastName(this.lastName());
        user.setEmail(this.email());
        user.setPassword(this.password());
        user.setPhoneNumber(this.phoneNumber());

        return user;
    }

}
