import User from "../models/entity/User";
import UserHelper from "../models/helper/user.helper";
import { UserRepository } from "../repository/User.repository"
import List from "../utils/List";

export default class UserService
{
    private repository: UserRepository;

    public UserService(repository: UserRepository)
    {
        this.repository = repository;
    }

    public findAll(): List<User>
    {
        // let userList: List<User> = this.repository.find();

        // return UserHelper.convertAll(userList);
    }
    
}