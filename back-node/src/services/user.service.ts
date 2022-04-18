import UserDTO from "../models/DTO/UserDTO";
import UserHelper from "../models/helper/user.helper";
import { UserRepository } from "../repository/User.repository"
import List from "../utils/List";

export default class UserService
{
    private static repository: UserRepository;

    public UserService(repository: UserRepository)
    {
        UserService.repository = repository;
    }

    public static findAll(): List<UserDTO>
    {
        let userListDb = this.repository.find();
        let userList = new List<UserDTO>();

        userListDb.forEach(user => {
            userList.add(UserHelper.convert(user));
        })
        
        return userList;
    }
    
}