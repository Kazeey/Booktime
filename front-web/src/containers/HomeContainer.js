import React from 'react'
import PropTypes from 'prop-types'
import Modal from '../components/modal/index';
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
