import express, {Request, Response } from "express";
import User from "../models/User";
import List from "../utils/List";
import { collections } from "../services/database.service";
import { ObjectId } from "bson";
import sendMail from "../services/mail.service";
import { reduceEachLeadingCommentRange } from "typescript";
import * as jwt from 'jsonwebtoken';
import { Constants } from "../config";

export default class UserController
{

    public UserController() { }

    public async findAll (req: Request, res: Response)  
    {
        try
        {
            let usersToSend: Array<User> = new Array<User>();
            const usersDB = (collections.user?.find() as unknown as List<User>);
            
            await usersDB.forEach((user:User) => {
                usersToSend.push(user);
            });
            
            res.status(200).send(usersToSend);
        }
        catch(e)
        {
            console.log("catch error: ", e);
            res.status(500).send(e);
        }
    }

    public async findOne (req: Request, res: Response)
    {
        try
        {
            let userToSend: User;
            const { mail, password } = req.body;
            
            userToSend = await (collections.user?.findOne({email: mail, password: password}) as unknown as User);
            
            res.status(200).send(userToSend);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async findOneById (req: Request, res: Response)
    {
        try
        {
            let userToSend: User;
            let body = req.body;
            
            userToSend = await (collections.user?.findOne({_id : new ObjectId(body.id)}) as unknown as User);
            
            res.status(200).send(userToSend);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async add (req: Request, res: Response)
    {
        try
        {
            let body = req.body;

            let userToAdd: User = new User(body.name, body.firstname, body.pseudo, body.email, body.password, body.birthdate, body.base64, body.status);

            await collections.user?.insertOne(userToAdd);

            sendMail(body.mail, "Bienvenue sur le site de la bibliothèque", "Vous êtes bien inscrit sur le site de la bibliothèque. Vous pouvez maintenant vous connecter sur le site.");

            res.status(200).send(userToAdd);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async update (req: Request, res: Response)
    {
        try
        {
            let body = req.body;

            let userToUpdate: User = new User(body.name, body.firstname, body.pseudo, body.email, body.password, body.birthdate, body.base64, body.status);

            await collections.user?.updateOne({_id : new ObjectId(body.id)}, userToUpdate);

            sendMail(body.mail, "Modifications de vos informations", "Vos informations ont bien été modifiées.");

            res.status(200).send(userToUpdate);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async delete (req: Request, res: Response)
    {
        try
        {
            let body = req.body;

            await collections.user?.deleteOne({_id : new ObjectId(body.id)});

            res.status(200);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async auth(req: Request, res: Response) 
    {
        const { email, password } = req.body;
        
        try {
            let user = await collections.user?.findOne({ email: email, password: password });

            if (!user)
            {
                res.status(404).send("404 - User not found");
                return false;
            }

            delete user.password;

            const expireIn = 24 * 60 * 60;
            const token = jwt.sign({ user: user }, Constants.jwtSecret, { expiresIn: expireIn });
            
            res.header('Authorization', 'Bearer' + token);
            return res.status(200).json(user);
        } 
        catch (e) {
            res.status(500).send(e);
        }
    }
}