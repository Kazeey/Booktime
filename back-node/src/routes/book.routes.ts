import express, { Request, Response } from 'express';
import BookController from '../controllers/book.controller';

export const router = express.Router();
router.use(express.json());

let BookControllerInstance = new BookController();

router.get('/findAll', async (req: Request, res: Response) => await BookControllerInstance.findAll(req, res));
router.post('/findOne', async (req: Request, res: Response) => await BookControllerInstance.findOne(req, res));
router.post('/findOneById', async (req: Request, res: Response) => await BookControllerInstance.findOneById(req, res));
router.post('/add', async (req: Request, res: Response) => await BookControllerInstance.add(req, res));
router.patch('/update', async (req: Request, res: Response) => await BookControllerInstance.update(req, res));
router.delete('/delete', async (req: Request, res: Response) => await BookControllerInstance.delete(req, res));
