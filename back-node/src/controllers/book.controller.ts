import express, {Request, Response } from "express";
import Book from "../models/Book";
import List from "../utils/List";
import { collections } from "../services/database.service";
import { ObjectId } from "bson";

let mongoToSqlConverter = require("mongo-to-sql-converter");

export default class BookController
{

    public BookController () { }

    public async findAll (req: Request, res: Response)
    {
        try
        {
            let booksToSend: Array<Book> = new Array<Book>();
            const booksDB = (collections.book?.find() as unknown as List<Book>);
            
            await booksDB.forEach((book:Book) => {
                booksToSend.push(book);
            });

            res.status(200).send(booksToSend);
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
            let booksToSend: Book;
            let body = req.body;
            
            booksToSend = await (collections.book?.findOne({title: body.title}) as unknown as Book);
            
            res.status(200).send(booksToSend);
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
            let bookToSend: Book;
            let body = req.body;
            
            bookToSend = await (collections.book?.findOne({_id : new ObjectId(body.id)}) as unknown as Book);
            
            res.status(200).send(bookToSend);
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
            let bookToAdd: Book = new Book(body.title, body.synopsis, body.ISBN, body.publicationDate, body.category, body.pageCount, body.authorsId, body.averageRating, body.base64);
            const mongoQuery = "db.book.find({title: 'Harry Potter et la coupe de feu'})";
            await collections.book?.update({title: bookToAdd.getTitle().toString(), upsert: true}, bookToAdd);
            const SQLQuery = mongoToSqlConverter.convertToSQL(mongoQuery, true);

            console.log(SQLQuery);
            res.status(200).send(bookToAdd);
        }
        catch(e)
        {
            console.log("catch error: ", e);
            res.status(500).send(e);
        }
    }

    public async addLocally (book: Book)
    {
        try
        {
            await collections.book?.update({title: book.getTitle()}, book, {upsert: true});
            
            return true;
        }
        catch(e)
        {
            throw e;
        }
    }

    public async update (req: Request, res: Response)
    {
        try
        {
            let body = req.body;
            let bookToUpdate: Book = new Book(body.title, body.synopsis, body.ISBN, body.publicationDate, body.category, body.pageCount, body.authorsId, body.averageRating, body.base64);
            
            await collections.book?.updateOne({_id : new ObjectId(body.id)}, bookToUpdate);
            
            res.status(200).send(bookToUpdate);
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
            
            await collections.book?.deleteOne({_id : new ObjectId(body.id)});
            
            res.status(200);
        }
        catch(e)
        {
            res.status(500).send(e);
        }
    }
}