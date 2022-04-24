import express, { Request, Response } from 'express';

export default class collectApiDataController 
{
    public collectApiDataController () { }

    public async dataCollect(req: Request, res: Response)
    {
        try
        {
            let url: string;
            

        }
        catch (e)
        {
            res.status(500).send(e);
        }
    }
}