import React from 'react'
import PropTypes from 'prop-types'
import { Box, Button, FormControl, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import modalStyle from '../../utils/styles/modal.style';
import checkMail from '../../utils/functions/checkMailFormat';
import EmailIcon from '@mui/icons-material/Email';
import CloseIcon from '@mui/icons-material/Close';

const ForgotPassword = ( props ) => {    
  const [values, setValues] = React.useState({
    email: '',
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value })
  };

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