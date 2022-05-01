import { Constants } from "../config";

export default function checkKey(key: String): string
{
    if (key == undefined)
        return Constants.nonAcquis;
    else 
        return key.toString();
}