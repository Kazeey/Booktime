import express, { Request, Response } from 'express';
import UserController from '../controllers/user.controller';
import Security from '../middleware/security';

export const router = express.Router();
router.use(express.json());

let UserControllerInstance = new UserController();
let securityControllerInstance = new Security();

router.get('/findAll', async (req: Request, res: Response) => await UserControllerInstance.findAll(req, res));
router.post('/findOne', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await UserControllerInstance.findOne(req, res));
router.post('/findOneById', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await UserControllerInstance.findOneById(req, res));
router.post('/add', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await UserControllerInstance.add(req, res));
router.patch('/update', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await UserControllerInstance.update(req, res));
router.delete('/delete', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await UserControllerInstance.delete(req, res));
router.post('/auth', async (req: Request, res: Response) => await UserControllerInstance.auth(req, res));