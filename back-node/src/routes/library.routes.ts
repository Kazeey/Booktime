import express, { Request, Response } from 'express';
import LibraryController from '../controllers/library.controller';
import Security from '../middleware/security';

export const router = express.Router();
router.use(express.json());

let LibraryControllerInstance = new LibraryController();
let securityControllerInstance = new Security();

router.get('/findAll', async (req: Request, res: Response) => await LibraryControllerInstance.findAll(req, res));
router.post('/findOne', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await LibraryControllerInstance.findOne(req, res));
router.post('/findOneById', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await LibraryControllerInstance.findOneById(req, res));
router.post('/add', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await LibraryControllerInstance.add(req, res));
router.patch('/update', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await LibraryControllerInstance.update(req, res));
router.delete('/delete', securityControllerInstance.checkJWT, async (req: Request, res: Response) => await LibraryControllerInstance.delete(req, res));
