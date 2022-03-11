import React from 'react'
import styleModal from '../../utils/styles/modal';
import Authentication from '../authentication';
import Register from '../register';
import { Button, FormControl, FormHelperText, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import modalStyle from '../../utils/styles/modal.style';

const Modal = () => {
  const [moduleStatus, setModule] = React.useState(true);
  const [openStatus, setOpen] = React.useState(false);
  const [values, setValues] = React.useState("S'inscrire");

  const onOpenModal = (event, module) => {
    setOpen(Boolean(event.target.value));     
    setModule(module);
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
      <button type="button" value={true} onClick={(event) => onOpenModal(event, true)}> 
        Authentification
      </button>
      <button type="button" value={true} onClick={(event) => onOpenModal(event, false)}> 
        Enregistrement
      </button>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={openStatus}
        value = {false}
        onClose={(event) => onCloseModal(event)}
        BackdropComponent={styleModal.Backdrop}
      >
        <div>
          {moduleStatus ? <Authentication /> : <Register />}
          <FormControl variant='standard'>
            <Button variant="contained" color="success" sx={modalStyle.ButtonSwap} onClick={swapModule}>
              {values}
            </Button>
          </FormControl>
        </div>
      </styleModal.StyledModal>
    </div>
  )
}

export default Modal