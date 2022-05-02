import express, { Request, Response } from 'express';
import AuthorController from '../controllers/author.controller';
import Security from '../middleware/security';

export const router = express.Router();
router.use(express.json());

let AuthorControllerInstance = new AuthorController();
let securityControllerInstance = new Security();

router.get('/findAll', async (req: Request, res: Response) => await AuthorControllerInstance.findAll(req, res));
router.post('/findOne', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await AuthorControllerInstance.findOne(req, res));
router.post('/findOneById', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await AuthorControllerInstance.findOneById(req, res));
router.post('/add', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await AuthorControllerInstance.add(req, res));
router.patch('/update', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await AuthorControllerInstance.update(req, res));
router.delete('/delete', async (req: Request, res: Response) => await AuthorControllerInstance.delete(req, res));
