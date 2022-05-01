import express, { Request, Response } from 'express';
import AuthorController from '../controllers/author.controller';

export const router = express.Router();
router.use(express.json());

let AuthorControllerInstance = new AuthorController();

router.get('/findAll', async (req: Request, res: Response) => await AuthorControllerInstance.findAll(req, res));
router.post('/findOne', async (req: Request, res: Response) => await AuthorControllerInstance.findOne(req, res));
router.post('/findOneById', async (req: Request, res: Response) => await AuthorControllerInstance.findOneById(req, res));
router.post('/add', async (req: Request, res: Response) => await AuthorControllerInstance.add(req, res));
router.patch('/update', async (req: Request, res: Response) => await AuthorControllerInstance.update(req, res));
router.delete('/delete', async (req: Request, res: Response) => await AuthorControllerInstance.delete(req, res));
