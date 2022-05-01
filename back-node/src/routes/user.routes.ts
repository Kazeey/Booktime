import express, { Request, Response } from 'express';
import UserController from '../controllers/user.controller';

export const router = express.Router();
router.use(express.json());

let UserControllerInstance = new UserController();

router.get('/findAll', async (req: Request, res: Response) => await UserControllerInstance.findAll(req, res));
router.post('/findOne', async (req: Request, res: Response) => await UserControllerInstance.findOne(req, res));
router.post('/findOneById', async (req: Request, res: Response) => await UserControllerInstance.findOneById(req, res));
router.post('/add', async (req: Request, res: Response) => await UserControllerInstance.add(req, res));
router.patch('/update', async (req: Request, res: Response) => await UserControllerInstance.update(req, res));
router.delete('/delete', async (req: Request, res: Response) => await UserControllerInstance.delete(req, res));
