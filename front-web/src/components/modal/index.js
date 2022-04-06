import React from 'react'
import Authentication from '../authentication';
import Register from '../register';
import { Button, Fade, FormControl } from '@mui/material';

import modalStyle from '../../utils/styles/modal.style';
import styleModal from '../../utils/styles/modal';
import buttonStyle from '../../utils/styles/button.style';

const Modal = () => {
  const [moduleStatus, setModule] = React.useState(true);
  const [openStatus, setOpen] = React.useState(false);
  const [values, setValues] = React.useState("S'inscrire");

  const onOpenModal = (event, module, other) => {
    setOpen(Boolean(event.target.value));     
    setModule(module);
    setValues(other);
  };

  const onCloseModal = (event) => {
    setOpen(Boolean(event.target.value));
  };

  const swapModule = () => {
    setModule(!moduleStatus);
    setValues(!moduleStatus ? "S'inscrire" : "Se connecter");
  }

  return (
    <div>
      <FormControl variant='standard'>
        <Button variant='outlined' sx={buttonStyle.loginButton} value={true} onClick={(event) => onOpenModal(event, true, "S'inscrire")}>
          Se connecter
        </Button>
      </FormControl>
      <FormControl variant='standard'>
        <Button variant='contained' sx={buttonStyle.loginButton} value={true} onClick={(event) => onOpenModal(event, false, "Se connecter")}>
          Enregistrement
        </Button>
      </FormControl>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={openStatus}
        value = {false}
        onClose={(event) => onCloseModal(event)}
        BackdropComponent={styleModal.Backdrop}
      >
        <Fade in={openStatus}>
          <div>
            {moduleStatus ? <Authentication /> : <Register />}
            <FormControl variant='standard'>
              <Button variant="contained" color="success" sx={modalStyle.buttonSwap} onClick={swapModule}>
                {values}
              </Button>
            </FormControl>
          </div>
        </Fade>
      </styleModal.StyledModal>
    </div>
  )
}

export default Modal