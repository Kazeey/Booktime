import React from 'react'
import PropTypes from 'prop-types'
import { Box, Button, FormControl, Input, InputAdornment, InputLabel } from '@mui/material';
import checkMail from '../../utils/functions/checkMailFormat';
import { setMessage } from '../../utils/functions/setMessage';

import modalStyle from '../../utils/styles/modal.style';
import EmailIcon from '@mui/icons-material/Email';

const ForgotPassword = ( props ) => {    
  const [values, setValues] = React.useState({
    email: '',
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value })
  };

  const forgetPassword = (email) => {
    let mail = {
      "email" : email
    }

    forgetPassword(mail)
    .then(response => {
      setMessage("Si l'adresse mail est correcte, vous recevrez un email avec un lien pour réinitialiser votre mot de passe.");
    });
  }

  return (
    <Box sx={modalStyle.forgetPasswordBox}>      
      <h1 style={modalStyle.h1}>Mot de passe oublié</h1>
      
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

      <div style={{width : "50%", display: "flex", alignItems: "stretch", justifyContent: "center"}}>
        <Box>
          <FormControl variant='standard' sx={modalStyle.formControlForgetPassword} value={false} onClick={(event) => props.func(event)}>
            <Button variant="contained" color="error" sx={modalStyle.buttonForm} style={{width: '50%'}}>
              Annuler
            </Button>
          </FormControl>
        </Box>
        <Box>
          <FormControl variant='standard' sx={modalStyle.formControlForgetPassword}>
            <Button variant="contained" color="success" sx={modalStyle.buttonForm} style={{width: '50%'}}
              disabled={
                !values.email || !checkMail(values.email)
              }
            >
              Envoyer
            </Button>
          </FormControl>
        </Box>
      </div>      
    </Box>
  )
}

ForgotPassword.propTypes = {
    email : PropTypes.string,
}

export default ForgotPassword