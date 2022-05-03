import React from 'react'
import { Box } from '@mui/system'
import PropTypes from 'prop-types'
import { Button, FormControl, IconButton, Input, InputAdornment, InputLabel } from '@mui/material';
import EmailIcon from '@mui/icons-material/Email';
import HttpsIcon from '@mui/icons-material/Https';
import PersonIcon from '@mui/icons-material/Person';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import modalStyle from '../../utils/styles/modal.style';
import checkMail from '../../utils/functions/checkMailFormat';
import { checkPassword, passwordStrength, samePassword } from '../../utils/functions/checkPassword';
import checkNameFormat from '../../utils/functions/checkNameFormat';
import { registerApi } from '../../services/UserService';
import { setMessage } from '../../utils/functions/setMessage';


const Register = () => {
  const [values, setValues] = React.useState({
    name : '',
    firstname : '',
    email: '',
    password: '',
    confirmPassword: '',
    sex : '',
    showPassword: false,
    showConfirmPassword : false,
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

  const handleClickShowConfirmPassword = () => {
    setValues({
      ...values,
      showConfirmPassword: !values.showConfirmPassword,
    });
  };

  const handleMouseDownConfirmPassword = (event) => {
    event.preventDefault();
  };

  const register = async (name, firstname, email, password) => {

    let user = {
      name : name,
      firstname : firstname,
      email: email,
      password: password,
      status : "active"
    }

    await registerApi(user)
    .then(response => {
      setMessage("Votre compte a été créé avec succès, vous pouvez maintenant vous connecter.");
    });
  }

  return (
    <Box sx={modalStyle.registerBox}>
      <h1 style={modalStyle.h1}>Formulaire d'inscription</h1>

      <FormControl variant="standard" sx={modalStyle.formControl}>
        <InputLabel htmlFor="nameInput">
          Nom
        </InputLabel>
        <Input
          id="nameInput"
          type='name'
          error={!checkNameFormat(values.name) && values.name !== ''}
          value={values.name}
          onChange={handleChange('name')}
          startAdornment={
            <InputAdornment position="start">
              <PersonIcon />
            </InputAdornment>
          }
        />
      </FormControl>

      <FormControl variant="standard" sx={modalStyle.formControl}>
        <InputLabel htmlFor="firstnameInput">
          Prénom
        </InputLabel>
        <Input
          id="firstnameInput"
          type='firstname'
          error={!checkNameFormat(values.firstname) && values.firstname !== ''}
          value={values.firstname}
          onChange={handleChange('firstname')}
          startAdornment={
            <InputAdornment position="start">
              <PersonIcon />
            </InputAdornment>
          }
        />
      </FormControl>

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

      <FormControl variant='standard' sx={modalStyle.formControl}>
        <InputLabel htmlFor="confirmPasswordInput">
          Confirmation du mot de passe
        </InputLabel>
        <Input
          id="confirmPasswordInput"
          type={values.showConfirmPassword ? 'text' : 'password'}
          value={values.confirmPassword}
          onChange={handleChange('confirmPassword')}
          startAdornment={
            <InputAdornment position="start">
              <HttpsIcon />
            </InputAdornment>
          }
          endAdornment={
            <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowConfirmPassword}
                onMouseDown={handleMouseDownConfirmPassword}
              >
                {values.showConfirmPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
          }
        />
        {          
          samePassword(values.password, values.confirmPassword)
        }
      </FormControl>
      
      <Box>
        <FormControl variant='standard' sx={modalStyle.formControl}>
          <Button variant="contained" color="success" sx={modalStyle.buttonForm} 
            disabled={
              !values.name || !checkNameFormat(values.name) || 
              !values.firstname || !checkNameFormat(values.firstname) ||
              !values.email || !checkMail(values.email) ||
              !values.password || !checkPassword(values.password) ||
              values.password !== values.confirmPassword
            }
            onClick={() => { register(values.name, values.firstname, values.email, values.password) }}
          >
            S'inscrire
          </Button>
        </FormControl>
      </Box>
      <div id="messageZone"></div>
    </Box>
  )
}


Register.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Register