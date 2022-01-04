import axios from 'axios';
import apiURL from '../../utils/constants/index';
import { getUsersList } from './UserService';

jest.mock("axios");

describe("getUsersList", () => {
    describe("When API call is successfull", () => {
        it("should return users list", async () => {
            const users = [
                // TODO : Il faut définir la structure d'un user dans la base
            ];
            axios.get.mockResolvedValue({users});

            const response = await getUsersList();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/users/findall');
            expect(response).toEqual(users);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty users list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getUsersList();

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/users/findall');
            expect(response).toEqual([]);
        });
    });
});

describe("getUserById", () => {
    describe("When API call is successfull", () => {
        it("should return users list", async () => {
            const users = [
                // TODO : Il faut définir la structure d'un user dans la base
            ];
            axios.get.mockResolvedValue({users});

            const response = await getUserById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/users/findById/1');
            expect(response).toEqual(users);
        });
    });

    describe("When API call fails", () => {
        it ("should return empty users list", async () => {
            const message = "Error";
            axios.get.mockRejectedValueOnce(new Error(message));

            const response = await getUserById(1);

            expect(axios.get).toHaveBeenCalledWith(apiURL + '/users/findById/1');
            expect(response).toEqual([]);
        });
    });
});
