import axios from 'axios';
import { apiURL } from '../utils/constants/constants';

const getBooksList = async () => {
    try
    {
        const response = await axios.get(apiURL + 'book/findAll/');
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const getBookById = async (id) => {
    try
    {
        const response = await axios.get(apiURL + 'book/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { getBooksList, getBookById };