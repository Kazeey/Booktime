import express, { Request, Response } from 'express';
import LibraryController from '../controllers/library.controller';

export const router = express.Router();
router.use(express.json());

let LibraryControllerInstance = new LibraryController();

router.get('/findAll', async (req: Request, res: Response) => await LibraryControllerInstance.findAll(req, res));
router.post('/findOne', async (req: Request, res: Response) => await LibraryControllerInstance.findOne(req, res));
router.post('/findOneById', async (req: Request, res: Response) => await LibraryControllerInstance.findOneById(req, res));
router.post('/add', async (req: Request, res: Response) => await LibraryControllerInstance.add(req, res));
router.patch('/update', async (req: Request, res: Response) => await LibraryControllerInstance.update(req, res));
router.delete('/delete', async (req: Request, res: Response) => await LibraryControllerInstance.delete(req, res));
