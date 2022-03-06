import React from 'react'
import styleModal from '../../utils/styles/modal';
import Authentication from '../authentication';

const Modal = () => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <button type="button" onClick={handleOpen}> 
        Open Modal
      </button>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={open}
        onClose={handleClose}
        BackdropComponent={styleModal.Backdrop}
      >
        <Authentication />
      </styleModal.StyledModal>
    </div>
  )
}

export default Modal