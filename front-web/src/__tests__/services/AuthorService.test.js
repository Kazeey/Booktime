import { getAuthorsList } from '../../services/AuthorService';

describe("AuthorService", () => {
    describe("API call is successfull", () => {
        it("should return authors list", done => {
            getAuthorsList()
            .then(response => {
                expect(response[0]).toEqual(
                    expect.objectContaining({
                        id : expect.any(String),
                        name : expect.any(String),
                        firstName : expect.any(String),
                        birthDate : expect.any(String),
                        biography : expect.any(String),
                        country : expect.any(String),
                        base64 : expect.any(String)
                    })
                )
                done();
            });
        });
    });
});