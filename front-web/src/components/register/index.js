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

const Register = ( props ) => {

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
      <h1 style={modalStyle.h1}>Formulaire d'inscription</h1>
      
    </Box>
  )
}


Register.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Register