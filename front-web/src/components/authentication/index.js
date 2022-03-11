import React from 'react'
import { Box } from '@mui/system'
import PropTypes from 'prop-types'
import { Button, FormControl, FormHelperText, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import EmailIcon from '@mui/icons-material/Email';
import HttpsIcon from '@mui/icons-material/Https';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import modalStyle from '../../utils/styles/modal.style';
import checkMail from '../../utils/functions/checkMailFormat';
import { checkPassword, passwordStrength } from '../../utils/functions/checkPassword';

const Authentication = ( props ) => {

  const [values, setValues] = React.useState({
    email: '',
    password: '',
    showPassword: false,
  });

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

  return (
    <Box sx={modalStyle.box}>
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
            error={!checkPassword(values.password) && values.password !== ''}
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
      <Box sx={modalStyle.boxButton}>
        <FormControl variant='standard' sx={modalStyle.formControl}>
          <Button variant="contained" color="success" sx={modalStyle.ButtonForm} 
            // disabled={
            //   // Ajouter condition ? true : false
            // }
          >
            Se connecter
          </Button>
        </FormControl>
      </Box>
    </Box>
  )
}


Authentication.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Authentication