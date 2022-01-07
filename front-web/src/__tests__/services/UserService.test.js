import { getUsersList } from "../../services/UserService";

describe("UserService", () => {
    describe("API call is successfull", () => {
        it("should return users list", done => {            
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
