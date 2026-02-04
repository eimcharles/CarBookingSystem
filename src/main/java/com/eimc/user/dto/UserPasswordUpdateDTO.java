package com.eimc.user.dto;

public record UserPasswordUpdateDTO(

        String oldPassword,
        String newPassword

) {
}
