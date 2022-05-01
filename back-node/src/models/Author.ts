import { ObjectId } from "mongoose";

export default class User 
{
    private _id: ObjectId;
    private name: String;
    private firstname: String;
    private birthDate: Date;
    private deathDate: Date;
    private biography: String;
    private country: String;
    private base64: String;

    constructor(name: String, firstname: String, birthDate: Date, deathDate: Date, biography: String, country: String, base64: String)
    {
        this.name = name;
        this.firstname = firstname;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.biography = biography;
        this.country = country;
        this.base64 = base64;
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

    public getBirthDate(): Date {
        return this.birthDate;
    }

    public setBirthDate(value: Date) {
        this.birthDate = value;
    }

    public getDeathDate(): Date {
        return this.deathDate;
    }

    public setDeathDate(value: Date) {
        this.deathDate = value;
    }

    public getBiography(): String {
        return this.biography;
    }

    public setBiography(value: String) {
        this.biography = value;
    }

    public getCountry(): String {
        return this.country;
    }

    public setCountry(value: String) {
        this.country = value;
    }

    public getBase64(): String {
        return this.base64;
    }

    public setBase64(value: String) {
        this.base64 = value;
    }
}