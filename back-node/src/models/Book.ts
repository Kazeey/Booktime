import { ObjectId } from "mongoose";
import List from "../utils/List";

export default class User 
{
    private _id: ObjectId;
    private title: String;
    private synopsis: String;
    private ISBN: Object;
    private publicationDate: String;
    private category: List<String>;
    private pageCount: String;
    private authorsId: List<String>;
    private base64: String;

    constructor(title: String, synopsis: String, ISBN: Object, publicationDate: String, category: List<String>, pageCount: String, authorsId: List<String>, base64: String)
    {
        this.title = title;
        this.synopsis = synopsis;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.category = category;
        this.pageCount = pageCount;
        this.authorsId = authorsId;
        this.base64 = base64;
    }

    public getId(): ObjectId {
        return this._id;
    }

    public setId(value: ObjectId) {
        this._id = value;
    }

    public getTitle(): String {
        return this.title;
    }

    public setTitle(value: String) {
        this.title = value;
    }

    public getSynopsis(): String {
        return this.synopsis;
    }

    public setSynopsis(value: String) {
        this.synopsis = value;
    }

    public getISBN(): Object {
        return this.ISBN;
    }

    public setISBN(value: Object) {
        this.ISBN = value;
    }

    public getPublicationDate(): String {
        return this.publicationDate;
    }

    public setPublicationDate(value: String) {
        this.publicationDate = value;
    }

    public getCategory(): List<String> {
        return this.category;
    }

    public setCategory(value: List<String>) {
        this.category = value;
    }

    public getPageCount(): String {
        return this.pageCount;
    }

    public setPageCount(value: String) {
        this.pageCount = value;
    }

    public getAuthorsId(): List<String> {
        return this.authorsId;
    }

    public setAuthorsId(value: List<String>) {
        this.authorsId = value;
    }

    public getBase64(): String {
        return this.base64;
    }

    public setBase64(value: String) {
        this.base64 = value;
    }

}