package com.eimc.user.dto;

import com.eimc.user.model.User;

public record UserUpdateDTO(

        String firstName,
        String lastName,
        ///  TODO add validation for email format
        String email,
        ///  TODO add validation for phone number format
        String phoneNumber

) {

    public User toEntity() {

        User user = new User();

        user.setFirstName(this.firstName());
        user.setLastName(this.lastName());
        user.setEmail(this.email());
        user.setPhoneNumber(this.phoneNumber());

        return user;
    }

}
