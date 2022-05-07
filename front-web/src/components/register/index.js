import React from 'react'
import PropTypes from 'prop-types'

import { registerApi } from '../../services/UserService';
import { setMessage } from '../../utils/functions/setMessage';
import { checkPassword, passwordStrength, samePassword } from '../../utils/functions/checkPassword';
import checkNameFormat from '../../utils/functions/checkNameFormat';
import checkMail from '../../utils/functions/checkMailFormat';

import { Button, FormControl, IconButton, Input, InputAdornment, InputLabel, Step, StepLabel, Stepper, TextField} from '@mui/material';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { Box } from '@mui/system'
import { Visibility, VisibilityOff } from '@mui/icons-material';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers';

import EmailIcon from '@mui/icons-material/Email';
import HttpsIcon from '@mui/icons-material/Https';
import PersonIcon from '@mui/icons-material/Person';

import modalStyle from '../../utils/styles/modal.style';

const steps = ['Phase 1', 'Phase 2'];

const getTodayDate = () => {
  const today = new Date();
  const mm = String(today.getDate()).padStart(2, '0');
  const dd = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
  const yyyy = today.getFullYear();

  return `${dd}/${mm}/${yyyy}`;
}

const Register = () => {
  const [activeStep, setActiveStep] = React.useState(0);
  const [date, setDate] = React.useState(getTodayDate());
  const [skipped, setSkipped] = React.useState(new Set());
  const [values, setValues] = React.useState({
    name : '',
    firstname : '',
    pseudo : '',
    email: '',
    password: '',
    confirmPassword: '',
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

  const isStepSkipped = (step) => {
    return skipped.has(step);
  }

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep(prevActiveStep => prevActiveStep + 1);
    setSkipped(newSkipped);
  }

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  }

  const register = async (name, firstname, pseudo, email, password, date) => {

    let user = {
      name : name,
      firstname : firstname,
      pseudo : pseudo,
      email: email,
      password: password,
      birthdate: date,
      status : "active"
    }

    await registerApi(user)
    .then(response => {
      if (response.data == "Cet email est déjà utilisé") 
        setMessage(response.data);
      else
        setMessage("Votre compte a été créé avec succès");
    });
  }

  return (
    <Box sx={modalStyle.registerBox}>
      <h1 style={modalStyle.h1}>Formulaire d'inscription</h1>
      <Stepper activeStep={activeStep}>
        {
          steps.map((label, index) => {
            const stepProps = {};
            const labelProps = {};

            return (
              <Step key={label} {...stepProps}>
                <StepLabel {...labelProps}>{label}</StepLabel>
              </Step>
            );
          })
        }
      </Stepper>
        <React.Fragment>
          { activeStep == 0 ? 
            <Box sx={modalStyle.firstBox}>
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
            </Box>
          :
            <div>
              <Box sx={modalStyle.secondBox}>
                <FormControl variant="standard" sx={modalStyle.formControl}>
                  <InputLabel htmlFor="pseudoInput">
                    Pseudo
                  </InputLabel>
                  <Input
                    id="pseudoInput"
                    type='pseudo'
                    error={!checkNameFormat(values.pseudo) && values.pseudo !== ''}
                    value={values.pseudo}
                    onChange={handleChange('pseudo')}
                    startAdornment={
                      <InputAdornment position="start">
                        <PersonIcon />
                      </InputAdornment>
                    }
                  />
                </FormControl>
                <br/>
                <FormControl variant="standard" sx={modalStyle.formControl}>
                  <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DesktopDatePicker
                      id="dateInput"
                      label="Date de naissance"
                      type='date'
                      inputFormat="dd/MM/yyyy"
                      value={date}
                      onChange={(event) => setDate(event)}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </FormControl>
                <br/>
                <br/>
                <br/>
                <br/>
                <FormControl variant='standard' sx={modalStyle.formControl}>
                  <Button variant="contained" color="success" sx={modalStyle.buttonForm} 
                    disabled={
                      !values.name || !checkNameFormat(values.name) || 
                      !values.firstname || !checkNameFormat(values.firstname) ||
                      !values.pseudo || !checkNameFormat(values.pseudo) ||
                      !values.email || !checkMail(values.email) ||
                      !values.password || !checkPassword(values.password) ||
                      values.password !== values.confirmPassword
                    }
                    onClick={() => { register(values.name, values.firstname, values.pseudo, values.email, values.password, values.date) }}
                  >
                    S'inscrire
                  </Button>
                </FormControl>
              </Box>
              <div id="messageZone"></div>
            </div>
                      
          }
          <Box sx={modalStyle.buttonPhase}>
            <Button
              color="inherit"
              disabled={activeStep === 0}
              onClick={handleBack}
              sx={{ mr: 1 }}
            >
              Back
            </Button>
            <Box sx={{ flex: "1 1 auto" }} />
            <Button
              onClick={handleNext}
              disabled={activeStep === steps.length - 1 || 
                !values.name || !checkNameFormat(values.name) || 
                !values.firstname || !checkNameFormat(values.firstname) ||
                !values.email || !checkMail(values.email) ||
                !values.password || !checkPassword(values.password) ||
                values.password !== values.confirmPassword
                ? true : false
              }
            >
              Next
            </Button>
          </Box>
        </React.Fragment>
    </Box>
  )
}


Register.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Register