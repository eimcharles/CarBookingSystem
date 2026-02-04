package com.eimc.user.dto;

public record UserPasswordUpdateDTO(

        ///  TODO add validation for password payload
        String oldPassword,
        String newPassword

) {

}
