import axios from 'axios';
import { apiURL } from '../../utils/constants/index';

const getBooksList = async () => {
    try
    {
        const response = await axios.get(apiURL + 'books/findall/');
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
        const response = await axios.get(apiURL + 'books/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { getBooksList, getBookById };