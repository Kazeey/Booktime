import { Constants } from "../config";
import express, { Request, Response } from "express";
import fs from "fs";

export default class ImageConvert
{
    public async saveImage(req: Request, res: Response): Promise<string>
    {
        let url = req.body.imageUrl;
        let filename = "image.jpg"
        return new Promise((resolve, reject) =>
        {
            const file = fs.createWriteStream(`${Constants.imagePath}${filename}`);
            const request = require("request");
            request(url).pipe(file);
            file.on("finish", () =>
            {
                resolve(filename);
            });
            file.on("error", (err) =>
            {
                reject(err);
            });
        });
    }

    public async deleteImage(filename: string): Promise<void>
    {
        return new Promise((resolve, reject) =>
        {
            fs.unlink(`${Constants.imagePath}${filename}`, (err) =>
            {
                if (err)
                {
                    reject(err);
                }
                else
                {
                    resolve();
                }
            });
        });
    }

    public async convertImage(path: string): Promise<string>
    {
        return new Promise((resolve, reject) =>
        {
            fs.readFile(path, (err, data) =>
            {
                if (err)
                {
                    reject(err);
                }
                else
                {
                    resolve(data.toString("base64"));
                }
            });
        });
    }    
}