import React from 'react'
import PropTypes from 'prop-types'
import Modal from '../components/modal/index';
import "../assets/css/modalContainer.css";

const ModalContainer = props => {
    return (
        <div id='modalDiv'>
            <Modal />
        </div>
    )
}

ModalContainer.propTypes = {

}

export default ModalContainer
