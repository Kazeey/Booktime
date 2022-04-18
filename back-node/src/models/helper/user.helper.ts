import express from 'express';
import { ListFormat } from 'typescript';
import List from '../../utils/List';
import UserDTO from '../DTO/UserDTO';
import User from '../entity/User';


export default class UserHelper
{
    private UserHelper() {}

    public static convertAll(userList: List<User>): List<UserDTO>
    {
        let userDTOList: List<UserDTO> = new List<UserDTO>();

        userList.forEach((user:User) => {
            userDTOList.add(UserHelper.convert(user));
        })

        return userDTOList;
    }

    public static convert(user:User): UserDTO
    {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getFirstname(),
            user.getPseudo(),
            user.getEmail(),
            user.getPassword(),
            user.getBirthdate(),
            user.getBase64(),
            user.getStatus()
        );
    }
}