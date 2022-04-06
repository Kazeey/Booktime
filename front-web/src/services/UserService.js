import axios from 'axios';
import { constants } from '../utils/constants/constants';

const connectUser = async (user) => {
    try
    {
        const response = await axios.post(constants.apiURL + 'user/connect', user);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const forgetPassword = async (email) => {
    try
    {
        const response = await axios.post(constants.apiURL + 'user/forgetPassword', email);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const changeAccount = async (user) => {
    try
    {
        const response = await axios.post(constants.apiURL + 'user/changeAccount', user);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

const getUsersList = async () => {
    try
    {
        const response = await axios.get(constants.apiURL + 'user/findAll/');
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
        const response = await axios.get(constants.apiURL + 'user/findById/' + id);
        return response.data;
    }
    catch (error)
    {
        throw error;
    }
}

export { connectUser, forgetPassword, changeAccount, getUsersList, getUserById };

