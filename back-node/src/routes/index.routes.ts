import express from "express";
import { router as userRouter } from "./user.routes";
// import { router as bookRouter } from "./book.routes";
// import { router as authorRouter } from "./author.routes";
// import { router as libraryRouter } from "./library.routes";

const router = express.Router();

router.use('/user', userRouter);
// router.use('/book', bookRouter);
// router.use('/author', authorRouter);
// router.use('/library', libraryRouter);

router.get('/', (req: express.Request, res: express.Response) => res.send('Hello World!'));
router.get('/health', (req: express.Request, res: express.Response) => {
    const healthcheck = {
        uptime: process.uptime(),
        message: 'OK',
        timestamp: Date.now()
    };
    res.send(JSON.stringify(healthcheck));
});

module.exports = router;