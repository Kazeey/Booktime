import axios from 'axios';
import apiURL from '../../utils/constants/index';

let services = {

    getUsersList: () => {
        try
        {
            const response = axios.get(apiURL + '/user/findall');
            return response.data;
        }
        catch (error)
        {
            throw error;
        }
    },

    getUserById: (id) => {
        try
        {
            const response = axios.get(apiURL + '/user/findById/' + id);
            return response.data;
        }
        catch (error)
        {
            throw error;
        }
    }
}

export default services;