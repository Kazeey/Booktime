import express, {Request, Response } from "express";
import Author from "../models/Author";
import List from "../utils/List";
import { collections } from "../services/database.service";
import { ObjectId } from "bson";

export default class AuthorController
{

    public AuthorController () { }

    public async findAll (req: Request, res: Response)
    {
        try
        {
            let authorsToSend: Array<Author> = new Array<Author>();
            const authorsDB = (collections.author?.find() as unknown as List<Author>);
            
            await authorsDB.forEach((author:Author) => {
                authorsToSend.push(author);
            });

            res.status(200).send(authorsToSend);
        }
        catch (e)
        {
            res.status(500).send(e);
        }
    }

    public async findOne (req: Request, res: Response)
    {
        try
        {
            let authorToSend: Author;
            let body = req.body;
            
            authorToSend = await (collections.author?.findOne({name: body.name, firstname: body.firstname}) as unknown as Author);
            
            res.status(200).send(authorToSend);
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
            let authorToSend: Author;
            let body = req.body;
            
            authorToSend = await (collections.author?.findOne({_id : new ObjectId(body.id)}) as unknown as Author);
            
            res.status(200).send(authorToSend);
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

            let author = new Author(body.name, body.firstname, body.birthdate, body.deathdate, body.biography, body.country, body.base64);

            await collections.author?.insertOne(author);

            res.status(200);
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

            let author = new Author(body.name, body.firstname, body.birthdate, body.deathdate, body.biography, body.country, body.base64);

            await collections.author?.updateOne({_id : new ObjectId(body.id)}, author);

            res.status(200);
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

            await collections.author?.deleteOne({_id : new ObjectId(body.id)});

            res.status(200);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }
}