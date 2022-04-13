import express, { Request, Response } from 'express';
import User from "../models/entity/User";

const userController = require('../controllers/user.controller');

export const router = express.Router();
router.use(express.json());

router.get('/findAll', (req: Request, res: Response) => userController.findAll());

