import * as mongoDB from 'mongodb';
import { Constants } from '../config'

export const collections: { 
    user?: mongoDB.Collection, 
    book?: mongoDB.Collection 
    author?: mongoDB.Collection 
    library?: mongoDB.Collection 
} = {}

export async function connectToDatabase()
{
    const client: mongoDB.MongoClient = new mongoDB.MongoClient(Constants.dbUrl);

    await client.connect();

    const db: mongoDB.Db = client.db(Constants.dbName);

    const userCollection: mongoDB.Collection = db.collection(Constants.collections.user);
    const bookCollection: mongoDB.Collection = db.collection(Constants.collections.book);
    const authorCollection: mongoDB.Collection = db.collection(Constants.collections.author);
    const libraryCollection: mongoDB.Collection = db.collection(Constants.collections.library);

    collections.user = userCollection;
    collections.book = bookCollection;
    collections.author = authorCollection;
    collections.library = libraryCollection;


}