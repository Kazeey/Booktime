import axios from 'axios';
import { constants } from '../utils/constants/constants';

const loginApi = async (user) => {
    try
    {
        const response = await axios.post(constants.apiURL + 'user/auth', user);
        return response;
    }
    catch (error)
    {
        throw error;
    }
}

const registerApi = async (user) => {
    try
    {
        const response = await axios.post(constants.apiURL + 'user/register', user);
        return response;
    }
    catch (error)
    {
        throw error;
    }
}   

const forgetPasswordApi = async (email) => {
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

export { loginApi, registerApi, forgetPasswordApi, changeAccount, getUsersList, getUserById };

