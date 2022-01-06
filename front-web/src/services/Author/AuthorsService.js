import axios from 'axios';
import { apiURL } from '../../utils/constants/index';

const getAuthorsList = async () => {
    try
    {
        const response = await axios.get(apiURL + 'authors/findall/');
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const getAuthorById = async (id) => {
    try
    {
        const response = await axios.get(apiURL + 'authors/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { getAuthorsList, getAuthorById };

