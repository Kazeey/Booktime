package com.project.booktime.model.helper;

import com.project.booktime.model.dto.SignUpDTO;
import com.project.booktime.model.entity.User;

public class SignUpHelper {

    private SignUpHelper() { }

    public static User signUpDTOToUser(SignUpDTO signUpDTO) {
        return new User()
                .setEmail(signUpDTO.getEmail())
                .setPassword(signUpDTO.getPassword());
    }
}
