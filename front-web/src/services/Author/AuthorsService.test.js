import axios from 'axios';
import apiURL from '../../utils/constants/index';
import { getAuthorsList, getAuthorById } from './AuthorsService';

jest.mock("axios");

describe("getAuthorsList", () => {
    describe("When API call is successfull", () => {
        it("should return authors list", async () => {
            const authors = [
                // TODO : Il faut définir la structure d'un auteur dans la base
            ];
            axios.get.mockResolvedValue({authors});

            const response = await getAuthorsList();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/author/findall');
            expect(response).toEqual(authors);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty authors list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getAuthorsList();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/author/findall');
            expect(response).toEqual([]);
        });
    });
});

describe("getAuthorById", () => {
    describe("When API call is successfull", () => {
        it("should return authors list", async () => {
            const authors = [
                // TODO : Il faut définir la structure d'un auteur dans la base
            ];
            axios.get.mockResolvedValue({authors});

            const response = await getAuthorById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/author/findById/1');
            expect(response).toEqual(authors);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty authors list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getAuthorById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/author/findById/1');
            expect(response).toEqual([]);
        });
    });
});
