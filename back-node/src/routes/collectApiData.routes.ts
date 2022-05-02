import express, { Request, Response } from 'express';
import CollectApiDataController from '../controllers/collectApiData.controller';
import Security from '../middleware/security';
import Image from '../utils/Image';

export const router = express.Router();
router.use(express.json());

let CollectApiDataControllerInstance = new CollectApiDataController();
let securityControllerInstance = new Security();
let imageInstance = new Image();

router.get('/googleApi/database/addBooks', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await CollectApiDataControllerInstance.dataCollect(req, res));