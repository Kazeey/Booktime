import axios from 'axios';
import apiURL from '../../utils/constants/index';

let services = {

    getAuthorsList: () => {
        try
        {
            const response = axios.get(apiURL + '/author/findall');
            return response.data;
        }
        catch (error)
        {
            throw error;
        }
    },

    getAuthorById: (id) => {
        try
        {
            const response = axios.get(apiURL + '/author/findById/' + id);
            return response.data;
        }
        catch (error)
        {
            throw error;
        }
    }
}

export default services;