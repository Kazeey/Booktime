import { ObjectId } from "mongoose";
import List from "../utils/List";

export default class User 
{
    private _id: ObjectId;
    private userId: ObjectId;
    private bookIdList: List<ObjectId>;
 
    constructor(userId: ObjectId, bookIdList: List<ObjectId>)
    {
        this.userId = userId;
        this.bookIdList = bookIdList;
    }

    public getId(): ObjectId {
        return this._id;
    }

    public setId(value: ObjectId) {
        this._id = value;
    }

    public getUserId(): ObjectId {
        return this.userId;
    }

    public setUserId(value: ObjectId) {
        this.userId = value;
    }

    public getBookIdList(): List<ObjectId> {
        return this.bookIdList;
    }

    public setBookIdList(value: List<ObjectId>) {
        this.bookIdList = value;
    }

}