import express, {Request, Response } from "express";
import Library from "../models/Library";
import List from "../utils/List";
import { collections } from "../services/database.service";
import { ObjectId } from "bson";

export default class LibraryController
{

    public LibraryController () { }

    public async findAll (req: Request, res: Response)
    {
        try
        {
            let librariesToSend: Array<Library> = new Array<Library>();
            const librairiesDB = (collections.library?.find() as unknown as List<Library>);
            
            await librairiesDB.forEach((library:Library) => {
                librariesToSend.push(library);
            });

            res.status(200).send(librariesToSend);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }

    public async findOne (req: Request, res: Response)
    {
        try
        {
            let librariesToSend: Library;
            let body = req.body;
            
            librariesToSend = await (collections.library?.findOne({title: body.title}) as unknown as Library);
            
            res.status(200).send(librariesToSend);
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
            let librairyToSend: Library;
            let body = req.body;
            
            librairyToSend = await (collections.library?.findOne({_id : new ObjectId(body.id)}) as unknown as Library);
            
            res.status(200).send(librairyToSend);
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
            let library = new Library(body.title, body.author);
            
            await collections.library?.insertOne(library);
            
            res.status(200).send(library);
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
            let library = new Library(body.title, body.author);
            
            await collections.library?.updateOne({_id : new ObjectId(body.id)}, library);
            
            res.status(200).send(library);
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
            
            await collections.library?.deleteOne({_id : new ObjectId(body.id)});
            
            res.status(200);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }
}