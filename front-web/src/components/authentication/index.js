import React from 'react'
import { Box } from '@mui/system'
import PropTypes from 'prop-types'
import { FormControl, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import EmailIcon from '@mui/icons-material/Email';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import modalStyle from '../../utils/styles/modal.style';

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

  const checkMail = (email) => {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (re.test(String(email).toLowerCase())) 
      return true;
    else
      return false;
  }

  return (
    <Box sx={modalStyle.Box}>
      <h1 style={modalStyle.h1}>Formulaire de connexion</h1>
      <FormControl variant="standard" sx={modalStyle.formControl}>
        <InputLabel htmlFor="emailInput">
          With a start adornment
        </InputLabel>
        <Input
          id="emailInput"
          type='email'
          error={!checkMail(values.email)}
          value={values.email}
          onChange={event => handleChange('email')(event)}
          startAdornment={
            <InputAdornment position="start">
              <EmailIcon />
            </InputAdornment>
          }
        />
      </FormControl>

      <FormControl variant='standard' sx={modalStyle.formControl}>
          <InputLabel htmlFor="passwordInput">
            With a start adornment
          </InputLabel>
          <Input
            id="passwordInput"
            type={values.showPassword ? 'text' : 'password'}
            value={values.password}
            onChange={handleChange('password')}
            startAdornment={
              <InputAdornment position="start">
                <EmailIcon />
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
      </FormControl>
    </Box>
  )
}

Authentication.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Authentication