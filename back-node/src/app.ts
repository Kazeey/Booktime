const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const http = require('http');

const { MongoClient, ServerApiVersion } = require('mongodb');

const app = express();
app.use(cors())

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

const config = require('./config');
const { port, dbUrl } = config;

const client = new MongoClient(dbUrl, { useNewUrlParser : true, useUnifiedTopology : true, serverApi: ServerApiVersion.v1 });

const routes = require('./routes/index.routes');
app.use(routes);

const httpServer = http.createServer(app);
httpServer.listen(port);

console.log(`Connected | Port ${port}`);