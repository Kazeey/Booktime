import axios from 'axios';
import { apiURL } from '../utils/constants/constants';

const getAuthorsList = async () => {
    try
    {
        const response = await axios.get(apiURL + 'author/findAll/');
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
        const response = await axios.get(apiURL + 'author/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { getAuthorsList, getAuthorById };

