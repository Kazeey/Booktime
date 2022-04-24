import express, { Request, Response } from 'express';
import CollectApiDataController from '../controllers/collectApiData.controller';
import Image from '../utils/Image';

export const router = express.Router();
router.use(express.json());

let CollectApiDataControllerInstance = new CollectApiDataController();
let imageInstance = new Image();

router.get('/googleApi/database/addBooks', async (req: Request, res: Response) => await CollectApiDataControllerInstance.dataCollect(req, res));
router.post('/image', async (req: Request, res: Response) => await imageInstance.encodeImageFromUrl(req, res));