import { MongoClient } from "mongodb";
import { MongoMemoryServer } from "mongodb-memory-server";

const book = [
    {
        title : "Harry Potter and the Philosopher's Stone",
        author : "J.K. Rowling",
        year : 1997,
        pages : 223,
        language : "English",
        isbn : "0-7475-3269-9",
        summary : "Harry Potter has no idea how famous he is. That's because he's being raised by his miserable aunt and uncle who are terrified Harry will learn that he's really a wizard, just as his parents were. But everything changes when Harry is summoned to attend an infamous school for wizards, and he begins to discover some clues about his illustrious birthright. From the surprising way he is greeted by a lovable giant, to the unique curriculum and colorful faculty at his unusual school, Harry finds himself drawn deep inside a mystical world he never knew existed and closer to his own noble destiny.",
        cover : "https://images-na.ssl-images-amazon.com/images/I/51Z%2B1rJqgGL._SX331_BO1,204,203,200_.jpg"
    }
];

describe ("BookService", () => {
    let con;
    let mongoServer;

    beforeAll(async () => {
        mongoServer = await MongoMemoryServer.create();
        con = await MongoClient.connect(mongoServer.getUri(), {});
    });

    afterAll(async () => {
        if (con)
            await con.close();
        if (mongoServer)
            await mongoServer.stop();
    });

    it("Should return books list", async () => {
        const db = con.db("books");

        expect(db).toBeDefined();
        const col = db.collection("books");
        const result = await col.insertMany(book, function(err, result) {
            if (err)
                throw err;
            expect(result.insertedCount).toBe(1);
        });
        expect(await col.countDocuments({})).toBe(1);
        expect(await col.find({}).toArray()).toEqual(book);
    });
});