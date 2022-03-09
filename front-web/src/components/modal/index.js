import React from 'react'
import styleModal from '../../utils/styles/modal';
import Authentication from '../authentication';
import Register from '../register';

const Modal = () => {
  const [moduleStatus, setModule] = React.useState('authentication');
  const [openStatus, setOpen] = React.useState(false);

  const changeModule = module => {
    setModule(module);
  };

  const modalStatus = open => {
    setOpen(open);
  };

  const onOpenModal = (event, module) => {
    modalStatus(event.target.value);     
    changeModule(module);
  };

  const onCloseModal = (event) => {
    modalStatus(event.target.value);
  };

  return (
    <div>
      <button type="button" value="true" onClick={(event) => onOpenModal(event, "authentication")}> 
        Authentification
      </button>
      <button type="button" value="true" onClick={(event) => onOpenModal(event, "register")}> 
        Enregistrement
      </button>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={openStatus}
        value = "false"
        onClose={(event) => onCloseModal(event)}
        BackdropComponent={styleModal.Backdrop}
      >
        {moduleStatus === "authentication" ? <Authentication /> : <Register />}
      </styleModal.StyledModal>
    </div>
  )
}

export default Modal