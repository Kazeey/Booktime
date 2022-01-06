import { apiURL } from "../../utils/constants/index";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import { getAuthorsList } from "./AuthorsService";
import { authors } from "../../utils/datasets/authors";

describe("AuthorService", () => {
    describe("API call is successfull", () => {
        it("should return authors list", done => {
            let mock = new MockAdapter(axios);

            mock.onGet(apiURL + "authors/findall/").reply(200, authors);

            getAuthorsList().then(response => {
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
