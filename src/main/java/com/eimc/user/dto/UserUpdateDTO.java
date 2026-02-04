package com.eimc.user.dto;

import com.eimc.user.model.User;

public record UserUpdateDTO(

        String firstName,
        String lastName,
        String email,
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
