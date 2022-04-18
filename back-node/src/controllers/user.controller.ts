import express, {Request, Response } from "express";
import User from "../models/entity/User";
import List from "../utils/List";
import UserService from "../services/user.service";

export default class UserController
{
    public static findAll(req: Request, res: Response)
    {
        let params = req;

        console.log(params);

        let test = UserService.findAll();

        console.log(test);
    }
}