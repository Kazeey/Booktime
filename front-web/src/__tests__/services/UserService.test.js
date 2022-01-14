import { MongoClient } from "mongodb";
import { MongoMemoryServer } from "mongodb-memory-server";

const user = [
    {
        username : "admin",
        name : "admin",
        firstname : "admin",
        password : "admin",
        email : "admin@test.com",
        role : "admin",
        image : "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
        isActive : true,
    }
];

describe("UserService", () => {
    let con;
    let mongoServer;

    beforeAll(async () => {
        mongoServer = await MongoMemoryServer.create();
        con = await MongoClient.connect(mongoServer.getUri(), {});
    });

    afterAll(async() => {
        if (con)
            await con.close();
        if (mongoServer)
            await mongoServer.stop();
    });

    it("Should return users list", async () => {
        const db = con.db("users");

        expect(db).toBeDefined();
        const col = db.collection("users");
        const result = await col.insertMany(user, function(err, result) {
            if (err)
                throw err;
            expect(result.insertedCount).toBe(1);
        });
        expect(await col.countDocuments({})).toBe(1);
        expect(await col.find({}).toArray()).toEqual(user);
    });
});