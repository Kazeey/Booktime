import { Constants } from "../config";
import fs from "fs";
import { v4 as uuidv4 } from "uuid";

const request = require("request");

export default class ImageConvert
{
    public async saveImage(urlAPI: String, path: string): Promise<string>
    {
        return new Promise((resolve, reject) =>
        {
            const file = fs.createWriteStream(path);
            request(urlAPI).pipe(file);
            file.on("finish", () =>
            {
                resolve(path);
            });
            file.on("error", (err) =>
            {
                reject(err);
            });
        });
    }

    public async deleteImage(path: string): Promise<void>
    {
        return await new Promise((resolve, reject) =>
        {
            fs.unlink(path, (err) =>
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

    public convertImageLocally(path: string): String
    {
        return fs.readFileSync(path).toString("base64");
    }

    public async sendBase64Image(urlAPI: String): Promise<void>
    {
        let filename = "image" + uuidv4() + ".jpg"
        let path = Constants.imagePath + filename;
        
        await this.saveImage(urlAPI, path);
        await this.convertImage(path).then((data) => {
            return data;
        });
        await this.deleteImage(path);
    }
}