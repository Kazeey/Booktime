import React from 'react'
import PropTypes from 'prop-types'
import { Box } from '@mui/system'
import { Button, Fade, FormControl, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import checkMail from '../../utils/functions/checkMailFormat';
import { checkPassword, passwordStrength } from '../../utils/functions/checkPassword';
import ForgotPassword from '../forgotPassword';
import { connectUser, changeAccount } from '../../services/UserService';
import { setMessage } from '../../utils/functions/setMessage';
import { constants } from '../../utils/constants/constants';

import modalStyle from '../../utils/styles/modal.style';
import styleModal from '../../utils/styles/modal';
import EmailIcon from '@mui/icons-material/Email';
import HttpsIcon from '@mui/icons-material/Https';

import { Navigate, useNavigate } from "react-router-dom";

const Authentication = () => {
  const [openStatus, setOpen] = React.useState(false);
  const [values, setValues] = React.useState({
    email: '',
    password: '',
    nbTry : 4,
    showPassword: false,
  });

  const navigate = useNavigate();

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value })
  };

  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const onOpenModal = (event) => {
    setOpen(Boolean(event.target.value));     
  };

  const onCloseModal = (event) => {
    setOpen(Boolean(event.target.value));
  };

  const connection = (email, password) => {
    let user = {
      "email" : email,
      "password" : password
    }

    connectUser(user)
    .then(response => {
      setMessage("");
      setValues({...values, nbTry : 4});
    })
    .catch(error => {
      if (values.nbTry > 0)
      {
        setValues({...values, nbTry : values.nbTry - 1});
        setMessage(constants.User.NOT_FOUND, values.nbTry);
      }

      if (values.nbTry === 0)
      {
        let user = {
          "email" : email,
          "status" : "blocked"
        }

        setMessage(constants.User.BLOCKED);
        changeAccount(user);
      }
      
    });
  }

  const bookContainer = () => {
    navigate('/books', { state: { from: 'authentication' } });
  }

  return (
    <Box sx={modalStyle.authenticationBox}>
      <h1 style={modalStyle.h1}>Formulaire de connexion</h1>

      <FormControl variant="standard" sx={modalStyle.formControl}>
        <InputLabel htmlFor="emailInput">
          Adresse mail
        </InputLabel>
        <Input
          id="emailInput"
          type='email'
          error={!checkMail(values.email) && values.email !== ''}
          value={values.email}
          onChange={handleChange('email')}
          startAdornment={
            <InputAdornment position="start">
              <EmailIcon />
            </InputAdornment>
          }
        />
      </FormControl>

      <FormControl variant='standard' sx={modalStyle.formControl}>
          <InputLabel htmlFor="passwordInput">
            Mot de passe
          </InputLabel>
          <Input
            id="passwordInput"
            type={values.showPassword ? 'text' : 'password'}
            value={values.password}
            onChange={handleChange('password')}
            startAdornment={
              <InputAdornment position="start">
                <HttpsIcon />
              </InputAdornment>
            }
            endAdornment={
              <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                >
                  {values.showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
            }
          />
          {   
            passwordStrength(values.password)
          }
      </FormControl>

      <Box>
        <FormControl variant='standard' sx={modalStyle.formControl}>
          <Button variant="contained" color="success" sx={modalStyle.buttonForm} 
            disabled={
              !values.email || !checkMail(values.email) || !values.password || !checkPassword(values.password)
            }
            // onClick={() => { connection(values.email, values.password) }}
            onClick={() => { bookContainer() }}
          >
            Se connecter
          </Button>
        </FormControl>
      </Box>

      <Box>
        <FormControl variant='standard' sx={modalStyle.formControl}>
          <Button variant="contained" color="success" value={true} sx={modalStyle.buttonForm} onClick={(event) => onOpenModal(event)} >
            Mot de passe oublié ?
          </Button>
        </FormControl>
      </Box>

      <p id="messageZone" style={{fontSize : '10px'}}></p>
      
      <styleModal.StyledModal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={openStatus}
        value={false}
        onClose={(event) => onCloseModal(event)}
        BackdropComponent={styleModal.Backdrop}
      >
        <Fade in={openStatus}>
          <div>
            <ForgotPassword func={onCloseModal}/>
          </div>
        </Fade>
      </styleModal.StyledModal>
    
    </Box>
  )
}


Authentication.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Authentication