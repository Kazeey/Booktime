import { MongoClient } from "mongodb";
import { MongoMemoryServer } from "mongodb-memory-server";

import { getAuthorsList } from "../../services/AuthorService";

const author = [
    {
        id : "1",
        name : "Joanne",
        firstname : "Rowling",
        birthdate : "1965-07-31",
        deathdate : "",
        country : "UK",
        biography : "Joanne Rowling is a British novelist, screenwriter and film producer. She is best known for her Harry Potter novels, which have won multiple awards and sold more than 500 million copies, and for her eponymous children's book series, which has been translated into fifty-eight languages and sold more than 500 million copies. She is also the author of the Harry Potter film series, which has won multiple awards and sold more than 80 million copies. She has also written the biography of J.K. Rowling, which has been adapted into a number of other media, and was the basis for the Harry Potter film series. She has also written a number of non-fiction books, most notably The Casual Vacancy, which was published in 2008."
    }
]

describe("AuthorService", () => {
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

    it("Should return authors list", async () => {
        const db = con.db("test");

        expect(db).toBeDefined();
        const col = db.collection("authors");
        const result = await col.insertOne(author);
        expect(result.insertedCount).toBe(1);
        expect(await col.countDocuments({})).toBe(1);
        expect(await col.find({}).toArray()).toEqual(author);
    });
});
