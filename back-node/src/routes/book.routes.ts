import express, { Request, Response } from 'express';
import BookController from '../controllers/book.controller';
import Security from '../middleware/security';

export const router = express.Router();
router.use(express.json());

let BookControllerInstance = new BookController();
let securityControllerInstance = new Security();

router.get('/findAll', async (req: Request, res: Response) => await BookControllerInstance.findAll(req, res));
router.post('/findOne', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await BookControllerInstance.findOne(req, res));
router.post('/findOneById', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await BookControllerInstance.findOneById(req, res));
router.post('/add', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await BookControllerInstance.add(req, res));
router.patch('/update', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await BookControllerInstance.update(req, res));
router.delete('/delete', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await BookControllerInstance.delete(req, res));
