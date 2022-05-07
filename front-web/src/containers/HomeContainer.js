import React, { useEffect } from 'react'
import { Navigate } from 'react-router-dom';
import "../assets/css/home.css";
import getLocalStorageKey from '../utils/functions/localStorage';

import ModalContainer from './ModalContainer';

const HomeContainer = props => {  
    useEffect(() => {
        if (getLocalStorageKey('token')) 
        {
        Navigate('/books', { state: { from: 'authentication' } });
        }
    });
    
    return (
        <div id='homeDiv'>
            <ModalContainer />
        </div>
    )
}

HomeContainer.propTypes = {

}

export default HomeContainer
