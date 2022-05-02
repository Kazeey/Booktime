import { Request, Response, NextFunction } from 'express';
import * as jwt from 'jsonwebtoken';
import { Constants } from '../config';


export default class Security
{
    public async checkJWT(req: Request, res: Response, next: NextFunction)
    {
        if (req.headers['Authorization'] == undefined)
            return false;

        let token: string = req.headers['Authorization'].toString();

        if(!!token && token.startsWith('Bearer'))
            token = token.slice(6, token.length);

        if (token)
        {
            await jwt.verify(token, Constants.jwtSecret, (err, decoded) => {
                if (err)
                {
                    console.log(err)
                    res.status(401).send({ auth: false, message: 'Failed to authenticate token.' });
                }
                else
                {
                    const expireIn = 24 * 60 * 60;
                    const newToken = jwt.sign({ user: res.locals.user }, Constants.jwtSecret, { expiresIn: expireIn });

                    res.header('Authorization', 'Bearer' + newToken);
                    next();
                }
            })
        }
    }
}