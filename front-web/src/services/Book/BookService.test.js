import { apiURL } from "../../utils/constants/index";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import { getBooksList } from "./BookService";
import { books } from "../../utils/datasets/books";

describe("BookService", () => {
    describe("API call is successfull", () => {
        it("should return books list", done => {
            let mock = new MockAdapter(axios);

            mock.onGet(apiURL + "books/findall/").reply(200, books);

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
