package com.eimc.user.dto;

import com.eimc.user.model.User;

public record UserRequestDTO(

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
        user.setPassword(this.password());          ///  TODO: Hash with Spring Security
        user.setPhoneNumber(this.phoneNumber());
        return user;
    }

}
