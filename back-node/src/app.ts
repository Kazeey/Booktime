const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const http = require('http');
const { MongoClient, ServerApiVersion } = require('mongodb');

import { Constants } from "./config";
import { connectToDatabase } from "./services/database.service";

const corsOptions = {
    exposedHeaders: 'Authorization',
}

const app = express();
app.use(cors(corsOptions))

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

const port = Constants.port;
const dbUrl = Constants.dbUrl;

const client = new MongoClient(dbUrl, { useNewUrlParser : true, useUnifiedTopology : true, serverApi: ServerApiVersion.v1 });

const routes = require('./routes/index.routes');
app.use(routes);

const httpServer = http.createServer(app);
httpServer.listen(port);

connectToDatabase()
.then(() => {
    console.log('Connected');
})
.catch(err => {
    console.log(err);
})

console.log(`Connected | Port ${port}`);


let test = client;