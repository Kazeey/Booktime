import axios from 'axios';
import { apiURL } from '../utils/constants/constants';

const getUsersList = async () => {
    try
    {
        const response = await axios.get(apiURL + 'user/findAll/');
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const getUserById = async (id) => {
    try
    {
        const response = await axios.get(apiURL + 'user/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { getUsersList, getUserById };

