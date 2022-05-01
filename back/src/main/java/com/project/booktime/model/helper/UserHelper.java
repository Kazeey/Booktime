package com.project.booktime.model.helper;

import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    private UserHelper() { }

    public static List<UserDTO> convertAll(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(convert(user));
        }

        return userDTOList;
    }

    public static UserDTO convert(User user) {
        return new UserDTO(
                user.getId(),
                user.getPseudo(),
                user.getName(),
                user.getFirstName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getBase64(),
                user.getLibrary(),
                user.getLiked()
        );
    }
}
