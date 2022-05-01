import { Constants } from "../config";

export default function checkDate(key: string): Date
{
    if (key == undefined)
        return Constants.noDate;
    else 
    {
        let date = new Date(key);
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear();
        
        return new Date(year + "/" + month + "/" + day);
    }
}