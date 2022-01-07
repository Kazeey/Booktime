import { getAuthorsList } from "../../services/AuthorService";

describe("AuthorService", () => {
    describe("API call is successfull", () => {
        it("should return authors list", done => {
            getAuthorsList()
            .then(response => {
                expect(response).toEqual(
                    expect.objectContaining({
                        id : expect.any(Number),
                        name : expect.any(String),
                        firstname : expect.any(String),
                        birthdate : expect.any(String),
                        city : expect.any(String),
                        country : expect.any(String),
                        image : expect.any(String)
                    })
                )
                done();
            });
        });
    });
});
