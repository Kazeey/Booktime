import { Constants } from "../config";
import express, { Request, Response } from "express";
import fs from "fs";

let fetch = require('node-fetch');

export default class ImageConvert
{
    public async convertImage (url: string)
    {
        return new Promise(async (resolve, reject) => {
            await fetch(url)
            .then((response: { ok: any; statusText: any; blob: () => any; }) => {
                if (!response.ok)
                    reject(`Error: ${response.statusText}`);
                return response.blob();
            })
            .then((blob: Blob) => {
                console.log("Avant blob");
                const reader = fs;
                let Url = new URL(url);
                console.log(Url);
                let contents = reader.readFileSync(Url.href, {encoding: 'base64'});
                return resolve(contents);
            });
        });
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