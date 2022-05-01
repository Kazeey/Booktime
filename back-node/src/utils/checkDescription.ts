import { Constants } from "../config";

export default function checkDescription(key: String): string
{
    if (key == undefined)
        return Constants.noDescription;
    else 
        return key.toString();
}