import React, { useEffect } from 'react'
import "../assets/css/home.css";

import ModalContainer from './ModalContainer';

const HomeContainer = props => {      
    return (
        <div id='homeDiv'>
            <ModalContainer />
        </div>
    )
}

HomeContainer.propTypes = {

}

export default HomeContainer
