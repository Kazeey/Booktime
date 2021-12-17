package com.project.booktime.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.project.booktime.params.Constants;

public class MongoDBConnection
{
    public MongoDatabase getDatabase ()
    {
        MongoClient mongoClient = MongoClients.create(Constants.MONGO_DB_CONNECTION_STRING);
        MongoDatabase database = mongoClient.getDatabase(Constants.MONGO_DB_DATABASE_NAME);
        return database;
    }

    public MongoCollection getCollection (MongoDatabase database, String collectionName)
    {
        MongoCollection collection = database.getCollection(collectionName);
        return collection;
    }
}
