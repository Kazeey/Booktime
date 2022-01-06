import { apiURL } from "../../utils/constants/index";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import { getUsersList } from "./UserService";
import { users } from "../../utils/datasets/users";

describe("UserService", () => {
    describe("API call is successfull", () => {
        it("should return users list", done => {
            let mock = new MockAdapter(axios);

            mock.onGet(apiURL + "users/findall/").reply(200, users);
            
            getUsersList().then(response => {
                expect(response).toEqual(
                    expect.objectContaining({
                        id: expect.any(Number),
                        name: expect.any(String),
                        firstname: expect.any(String),
                        age: expect.any(Number),
                        city: expect.any(String),
                        country: expect.any(String),
                        email: expect.any(String),
                        password: expect.any(String),
                        phone: expect.any(String),
                        address: expect.any(String),
                        zipcode: expect.any(String),
                        image: expect.any(String)
                    })
                )
                done();
            });
        });
    });
});
