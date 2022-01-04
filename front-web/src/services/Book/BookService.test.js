import axios from 'axios';
import apiURL from '../../utils/constants/index';
import { getBookslist } from './BookService';

jest.mock("axios");

describe("getBooksList", () => {
    describe("When API call is successfull", () => {
        it("should return books list", async () => {
            const books = [
                // TODO : Il faut définir la structure d'un autheur dans la base
            ];
            axios.get.mockResolvedValue({books});

            const response = await getBookslist();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/books/findall');
            expect(response).toEqual(books);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty books list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getBookslist();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/books/findall');
            expect(response).toEqual([]);
        });
    });
});

describe("getBookById", () => {
    describe("When API call is successfull", () => {
        it("should return books list", async () => {
            const books = [
                // TODO : Il faut définir la structure d'un autheur dans la base
            ];
            axios.get.mockResolvedValue({books});

            const response = await getBookById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/books/findById/1');
            expect(response).toEqual(books);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty books list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getBookById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/books/findById/1');
            expect(response).toEqual([]);
        });
    });
});
