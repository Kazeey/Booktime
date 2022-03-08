import React from 'react'
import styleModal from '../../utils/styles/modal';
import Authentication from '../authentication';
import Register from '../register';

const Modal = ({module}) => {
  const [values, setValues] = React.useState({
    open : false,
    authentication : true,
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value })
  };

  return (
    <div>
      <button type="button" value="true" onClick={handleChange('open')}> 
        Authentification
      </button>
      <button type="button" value="true" onClick={handleChange('open')}> 
        Enregistrement
      </button>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={values.open}
        value = "false"
        onClose={handleChange('open')}
        BackdropComponent={styleModal.Backdrop}
      >
        {values.authentication ? <Authentication /> : <Register />}
      </styleModal.StyledModal>
    </div>
  )
}

export default Modal