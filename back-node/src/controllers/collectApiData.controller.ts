import express, { Request, Response } from 'express';
import { Constants } from '../config';
import { URL } from 'url';
import fetch from 'node-fetch';
import List from '../utils/List';
import BookController from './book.controller';
import Book from '../models/Book';
import checkKey from '../utils/checkKey';
import checkDescription from '../utils/checkDescription';
import checkDate from '../utils/checkDate';
import ImageConvert from '../utils/Image';

export default class collectApiDataController 
{
    public collectApiDataController () { }

    public async dataCollect(req: Request, res: Response)
    {
        try
        {
            let url: URL;
            let bookControllerInstance: BookController = new BookController();
            let imageControllerInstance: ImageConvert = new ImageConvert();

            Constants.categories.forEach(async (category) =>{
                let nbStartIndex = 0;

                for (let i = 0; i < 3; i++)
                {
                    url = new URL(Constants.apiGoogleUrl + "subject:" + category + "&startIndex=" + nbStartIndex + "&maxResults=40&langRestrict=fr");
                    nbStartIndex += 40;

                    await fetch(url.toString(), { method: 'GET' })
                    .then (async response => {
                        if (response.status != 200)
                            throw new Error("Erreur lors de la récupération des données");

                        response.json().then(data => {
                            if (data.items == undefined)
                                return false;

                            data.items.forEach(async (book: any) => {     
                                let volumeInfo = book.volumeInfo;                         
                                let zTitle: String = "";
                                let zThumbnailBase64: any = "";
                                let zThumbnailBase64Promise: void;
                                let zThumbnail: string = "";
                                let lCategories: List<String> = new List<String>();
                                let lAuthors: List<String> = new List<String>();

                                if (volumeInfo.subtitle == undefined)
                                    zTitle = volumeInfo.title;
                                else
                                    zTitle = volumeInfo.title + " - " + volumeInfo.subtitle;
                                
                                let zAverageRating: string = checkKey(volumeInfo.averageRating);
                                let zDescription: string = checkDescription(volumeInfo.description);
                                let zPageCount: string = checkKey(volumeInfo.pageCount);
                                let zDate: Date = checkDate(volumeInfo.publishedDate);

                                if (volumeInfo.imageLinks == undefined || volumeInfo.imageLinks.thumbnail == undefined)
                                {
                                    zThumbnailBase64 = imageControllerInstance.convertImageLocally(Constants.noImage);
                                }
                                else
                                {
                                    let imageLinks = volumeInfo.imageLinks;
                                    zThumbnail = checkKey(imageLinks.thumbnail);
                                    zThumbnailBase64Promise = await imageControllerInstance.sendBase64Image(zThumbnail)
                                    .then(base64 => {
                                        zThumbnailBase64 = base64;
                                    });
                                }
                                
                                let industryIdentifiers: Object = volumeInfo.industryIdentifiers;

                                if (volumeInfo.categories != undefined)
                                    lCategories.fromArray(volumeInfo.categories);
                                else
                                    lCategories.add(category)

                                if (volumeInfo.authors)
                                    lAuthors.fromArray(volumeInfo.authors);
                                else
                                    lAuthors.add(Constants.nonAcquis);

                                let bookToAdd: Book = new Book(
                                    zTitle,
                                    zDescription,
                                    industryIdentifiers,
                                    zDate,
                                    lCategories,
                                    zPageCount,
                                    lAuthors,
                                    zAverageRating,
                                    zThumbnailBase64
                                );

                                console.log(bookToAdd.getTitle());
                                
                                await bookControllerInstance.addLocally(bookToAdd);
                            });
                        });

                        await this.delay(30000)
                    });
                }
            });
            
            console.log("Récupération des livres terminées");

        }
        catch (e)
        {
            res.status(500).send(e);
        }
    }

    public delay(ms: number)
    {
        return new Promise( resolve => setTimeout(resolve, ms) );
    }
}