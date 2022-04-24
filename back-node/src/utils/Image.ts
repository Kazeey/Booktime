import { Constants } from "../config";
import express, { Request, Response } from "express";

export default class ImageConvert
{
    public convertImage (url: string): Promise<string>
    {
        
    }

    public encodeImageFromUrl (req: Request, res: Response)
    {
        const url = req.body.imageUrl;

        this.convertImage(url).then((image) => {
            res.send(image);
        }).catch((error) => {
            res.status(500).send(error);
        });
    }
}