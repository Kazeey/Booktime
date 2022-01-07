import { getBooksList } from "../../services/BookService";

describe("BookService", () => {
    describe("API call is successfull", () => {
        it("should return books list", done => {
            getBooksList().then(response => {
                expect(response).toEqual(
                    expect.objectContaining({
                        id: expect.any(Number),
                        title: expect.any(String),
                        author: expect.any(String),
                        description: expect.any(String),
                        image: expect.any(String),
                        year: expect.any(Number),
                        rating: expect.any(Number)
                    })
                )
                done();
            });
        });
    });
});
