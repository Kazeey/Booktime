import { getBooksList } from '../../services/BookService';

describe("BookService", () => {
    describe("API call is successfull", () => {
        it("should return books list", done => {
            getBooksList()
            .then(response => {
                expect(response[0]).toEqual(
                    expect.objectContaining({
                        id : expect.any(String),
                        title : expect.any(String),
                        synopsis : expect.any(String),
                        publicationDate : expect.any(String),
                        category : expect.any(String),
                        pageCount : expect.any(Number),
                        rating : expect.any(Number),
                        authorId : expect.any(String),
                        base64 : expect.any(String)
                    })
                )
                done();
            });
        });
    });
});