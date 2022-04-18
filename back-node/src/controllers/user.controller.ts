import express, {Request, Response } from "express";
import User from "../models/entity/User";
import List from "../utils/List";

const userService = require('../services/user.service');

export default class UserController
{
    public findAll(req: Request, res: Response)
    {
        let params = req;

        console.log(params);

        userService.findAll()
        .then((users: List<User>)  => {
            res.send(users);
        })
    }
}