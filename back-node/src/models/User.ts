import { ObjectId } from "mongoose";

export default class User 
{
    private _id: ObjectId;
    private name: String;
    private firstname: String;
    private pseudo: String;
    private email: String;
    private password: String;
    private birthdate: Date;
    private base64: String;
    private status: String;

    constructor(name: String, firstname: String, pseudo: String, email: String, password: String, birthdate: Date, base64: String, status: String)
    {
        this.name = name;
        this.firstname = firstname;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.base64 = base64;
        this.status = status;
    }

    public getId(): ObjectId {
        return this._id;
    }

    public setId(value: ObjectId) {
        this._id = value;
    }

    public getName(): String {
        return this.name;
    }

    public setName(value: String) {
        this.name = value;
    }

    public getFirstname(): String {
        return this.firstname;
    }

    public setFirstname(value: String) {
        this.firstname = value;
    }

    public getPseudo(): String {
        return this.pseudo;
    }

    public setPseudo(value: String) {
        this.pseudo = value;
    }

    public getEmail(): String {
        return this.email;
    }

    public setEmail(value: String) {
        this.email = value;
    }

    public getPassword(): String {
        return this.password;
    }

    public setPassword(value: String) {
        this.password = value;
    }

    public getBirthdate(): Date {
        return this.birthdate;
    }

    public setBirthdate(value: Date) {
        this.birthdate = value;
    }

    public getBase64(): String {
        return this.base64;
    }

    public setBase64(value: String) {
        this.base64 = value;
    }

    public getStatus(): String {
        return this.status;
    }

    public setStatus(value: String) {
        this.status = value;
    }   
}