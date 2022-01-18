import { getUsersList } from '../../services/UserService';

describe("UserService", () => {
    describe("API call is successfull", () => {
        it("should return users list", done => {
            getUsersList()
            .then(response => {
                expect(response[0]).toEqual(
                    expect.objectContaining({
                        id : expect.any(String),
                        pseudo : expect.any(String),
                        name : expect.any(String),
                        firstName : expect.any(String),
                        birthday : expect.any(String),
                        email : expect.any(String),
                        base64 : expect.any(String)
                    })
                )
                done();
            });
        });
    });
});