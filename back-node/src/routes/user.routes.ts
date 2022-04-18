import express, { Request, Response } from 'express';
import UserController from '../controllers/user.controller';

export const router = express.Router();
router.use(express.json());

router.get('/findAll', (req: Request, res: Response) => UserController.findAll(req, res));

